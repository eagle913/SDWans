package com.eagle.function;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.WindowManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PicSelector {


    public static final int REQUEST_CROP_ICON = 0x0000000F;

    public static final int LOCAL_PIC_REQ_CODE = 111;
    public static final int TAKE_PHOTE_REQ_CODE = 2;
    public static final int FROM_ALBUMS_REQ_CODE = 3;
    public static final int PHOTO_ZOOM_REQ_CODE = 113;
    public static final int PICTURE_CROP_CODE = 4;

    public static final String TMP_PHOTO_NAME = "TMP_PHOTO_NAME";
    public static final String TMP_PHOTO_PATH = "TMP_PHOTO_PATH";
//	public static final String TMP_FILE_PATH = "TMP_FILE_PATH";

    public static String filePath = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + File.separator + "temp.png";

    private String tmpPhotoName = "head";
    private int maxSize = 6400;
    private static PicSelector ins = new PicSelector();
    private PicSelector(){}
    public static PicSelector getIns(){
        return ins;
    }

    public static void startPhotoZoom(Activity context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        startPhotoZoom(context, 720,null);
    }

    public static void startPhotoZoom(Activity context, int width,Uri uri) {
        if(uri == null){
            uri = Uri.fromFile(new File(filePath));
        }

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 4);
        intent.putExtra("aspectY", 3);
        intent.putExtra("outputX", width);
        intent.putExtra("outputY", width * 3 / 4);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("return-data", false);
        intent.putExtra("KEY_SCALE_UP_IF_NEEDED", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        try {
            context.startActivityForResult(intent, PHOTO_ZOOM_REQ_CODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void takePhoto(Activity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri uri = FileProvider.getUriForFile(context, "com.eagle.sdwan.fileprovider", new File(filePath));//通过FileProvider创建一个content类型的Uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    uri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
            if (null != context) {
                context.startActivityForResult(intent,
                        TAKE_PHOTE_REQ_CODE);
            }
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(new File(filePath)));

            if (null != context) {
                try {
                    context.startActivityForResult(intent,
                            TAKE_PHOTE_REQ_CODE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void fromAlbums(Activity context) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (null != context) {
            try {
                context.startActivityForResult(intent,
                        FROM_ALBUMS_REQ_CODE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Bitmap decodeUriAsBitmap(Context context) {
        Uri uri = Uri.fromFile(new File(filePath));
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(context.getContentResolver()
                    .openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    /**
     * 读取图片属性：旋转的角度
     *
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    public static void saveBitmap(Bitmap bitmap, String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
            FileOutputStream fOut = null;
            fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void activityResult(int requestCode, int resultCode, Intent data, Activity activity){
        switch (requestCode) {
            case TAKE_PHOTE_REQ_CODE:
                copyPhoto(filePath, tmpPhotoName, requestCode,activity);
                break;
            case FROM_ALBUMS_REQ_CODE:

                Uri originalUri = data.getData();
                startPhotoZoom(activity,120,originalUri);
//                String path = originalUri.toString();
//                // 2015.3.25 相册选取修改为和拍照一样的命名
//                // String fn = getFileName(originalUri);
//                // tmpPhotoName = fn + tmpPhotoName + ".png";
//
//                copyPhoto(path, tmpPhotoName, requestCode,activity);
//                try {
//                    activity.getContentResolver().openInputStream(originalUri);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }

                break;
        }
    }

    private void copyPhoto(final String filePath, final String tmpName,
                           final int type,final Activity activity) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                String newNath = activity.getCacheDir() + File.separator + tmpName;
                Bitmap b = null;
                float scale = 1;
                int nWidth = 0;
                int nHeight = 0;
                BitmapFactory.Options mOptions = new BitmapFactory.Options();
                if (TAKE_PHOTE_REQ_CODE == type) {
//                    BitmapFactory.Options mOptions = new BitmapFactory.Options();
                    mOptions.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(filePath, mOptions);
                    nWidth = mOptions.outWidth;
                    nHeight = mOptions.outHeight;
                } else {
                    try {
//                        Options mOptions = new Options();
                        mOptions.inJustDecodeBounds = true;
                        InputStream input = activity.getContentResolver().openInputStream(Uri.parse(filePath));
                        BitmapFactory.decodeStream(input, null, mOptions);
                        input.close();
                        nWidth = mOptions.outWidth;
                        nHeight = mOptions.outHeight;
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (nWidth > 0 && nHeight > 0) {

                    int size = nWidth * nHeight;

                    if(size > maxSize)
                    {
                        scale = size  / (float)maxSize;
                        scale = (float)Math.sqrt(scale);
                    }
                    Bitmap tmp = null;
                    mOptions = new BitmapFactory.Options();
                    mOptions.inJustDecodeBounds = false;
                    mOptions.inSampleSize = (int)(scale + 1);
                    if (TAKE_PHOTE_REQ_CODE == type) {
                        tmp = BitmapFactory.decodeFile(filePath, mOptions);
                    }
                    else
                    {
                        try{
                            InputStream input = activity.getContentResolver().openInputStream(Uri.parse(filePath));
                            tmp = BitmapFactory.decodeStream(input, null, mOptions);
                            input.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (null == tmp) {
                        onPhotoCopy("");
                    } else {
                        saveBitmap(tmp, newNath);
                        tmp.recycle();
                        tmp = null;
                        onPhotoCopy(newNath);
                    }
                } else {
                    onPhotoCopy("");
                }

            }
        }).start();
    }

    protected void onPhotoCopy(String path) {

    }

}
