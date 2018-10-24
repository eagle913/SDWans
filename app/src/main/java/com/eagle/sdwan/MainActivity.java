package com.eagle.sdwan;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.eagle.location.BDLocation;
import com.eagle.utils.SDLog;
import com.eagle.view.PopMore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener,PopMore.OnMoreItemClick{
    private static final int REQ_CODE_PERMISSION = 10000;
    private static final String TAG = "MainActivity";
    private static final int REQUEST_CODE_OPEN_DIRECTORY = REQ_CODE_PERMISSION + 1 ;
    private PopMore popMore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


           if( checkPermission()){
               location();
           }

        findViewById(R.id.imgMore).setOnClickListener(this);
           findViewById(R.id.imgHeader).setOnClickListener(this);


//        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
//        startActivityForResult(intent, REQUEST_CODE_OPEN_DIRECTORY);

    }

    private boolean checkPermission() {
        List<String> permissions = new ArrayList();
        if (checkCallingOrSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (checkCallingOrSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (checkCallingOrSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (checkCallingOrSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (checkCallingOrSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.CAMERA);
        }
        int size = permissions.size();
        if (size > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String[] arr = new String[size];
                permissions.toArray(arr);
                requestPermissions(arr, REQ_CODE_PERMISSION);

            } else {
                //TODO提示开启权限
            }
            return false;
        }
        return true;
    }

    private void location(){
        BDLocation bdLocation = new BDLocation();
        bdLocation.start(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        SDLog.d(TAG,"onActivityResult " + uri.toString());
        if(/*requestCode == REQ_CODE_PERMISSION*/ resultCode == RESULT_OK){

//            SDLog.d(TAG,"onActivityResult " + uri.toString());
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i = 0 ;i < permissions.length;i++){

            SDLog.d(TAG,"onRequestPermissionsResult requestCode " + requestCode + " p = " + permissions[i] + " g = " + grantResults[i]);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgMore:
                if(popMore == null){
                    popMore = new PopMore(this,this);
                }
                popMore.showWindow(view);
                break;
            case R.id.imgHeader:
                startActivity(new Intent(MainActivity.this,PersonalActivity.class));
                break;

        }
    }

    @Override
    public void onItemReport() {
        startActivity(new Intent(MainActivity.this,LogReportActivity.class));

    }

    @Override
    public void onItemLogs() {
        startActivity(new Intent(MainActivity.this,LogListActivity.class));

    }

    @Override
    public void onItemTraffic() {
        startActivity(new Intent(MainActivity.this,TrafficActivity.class));
    }

    @Override
    public void onItemConfig() {
        startActivity(new Intent(MainActivity.this,SettingActivity.class));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
