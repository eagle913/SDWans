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
import android.widget.Toast;

import com.eagle.location.BDLocation;
import com.eagle.utils.SDLog;
import com.eagle.view.PopMore;
import com.eagle.view.trendview.BrokenLineDimension;
import com.eagle.view.trendview.BrokenLineTrendData;
import com.eagle.view.trendview.BrokenLineTrendView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener,PopMore.OnMoreItemClick{
    private static final int REQ_CODE_PERMISSION = 10000;
    private static final String TAG = "MainActivity";
    private static final int REQUEST_CODE_OPEN_DIRECTORY = REQ_CODE_PERMISSION + 1 ;
    private PopMore popMore;

    private String[] mDefaultHorizontalText = new String[]{"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};

    private Double[] mScoreArr = new Double[]{200d, 220d, 30d, 40d, 190d, 140d, 260d, 0d, 40d, 178d, 250d, 140d};
    private Double[] mScoreArr2 = new Double[]{150d, 40d, 90d, 90d, 290d, 340d, 160d, 232d, 220d, 278d, 290d, 340d};
    private Double[] mScoreArr3 = new Double[]{90d, 80d, 170d, 290d, 230d, 240d, 260d, 332d, 120d, 78d, 90d, 40d};

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

        initTrendView();
    }

    private void initTrendView() {
        BrokenLineTrendView mBrokenLineTrendView = (BrokenLineTrendView) findViewById(R.id.trendView);
        List<Double> doubles = new ArrayList<>();
        doubles.add(0d);
        doubles.add(100d);
        doubles.add(200d);
        doubles.add(300d);
        doubles.add(400d);



        BrokenLineTrendData data = new BrokenLineTrendData();
        List<Double> doubles1 = Arrays.asList(mScoreArr);
        List<Double> doubles2 = Arrays.asList(mScoreArr2);
        List<Double> doubles3 = Arrays.asList(mScoreArr3);
        List<BrokenLineDimension> mDataList = new ArrayList<>();
        BrokenLineDimension d1 = new BrokenLineDimension();
        d1.mDatasList = doubles1;
        d1.mBrokenLineColor = getResources().getColor(R.color.color_01_line);
        d1.mBrokenPointColor = getResources().getColor(R.color.color_01_line);
        d1.mBrokenPointIntColor = getResources().getColor(R.color.color_01_point_in);
        d1.mBrokenPointOutColor = getResources().getColor(R.color.color_01_point_out);
        d1.remark = "上行";

        BrokenLineDimension d2 = new BrokenLineDimension();
        d2.mDatasList = doubles2;
        d2.mBrokenLineColor = getResources().getColor(R.color.color_02_line);
        d2.mBrokenPointColor = getResources().getColor(R.color.color_02_point);
        d2.mBrokenPointIntColor = getResources().getColor(R.color.color_02_point_in);
        d2.mBrokenPointOutColor = getResources().getColor(R.color.color_02_point_out);
        d2.remark = "下行";




        BrokenLineDimension d3 = new BrokenLineDimension();
        d3.mDatasList = doubles3;
        d3.mBrokenLineColor = getResources().getColor(R.color.colorPrimary);
        d3.mBrokenPointColor = getResources().getColor(R.color.colorPrimary);
        d3.mBrokenPointIntColor = getResources().getColor(R.color.colorPrimary);
        d3.mBrokenPointOutColor = getResources().getColor(R.color.colorPrimary);
        d3.remark = "职能体系";
        mDataList.add(d1);
        mDataList.add(d2);
//        mDataList.add(d3);


        data.mYLineDataList = doubles;
        data.mXLineDataList = Arrays.asList(mDefaultHorizontalText);
        data.mDimensionList = mDataList;
        data.mSelectColor = getResources().getColor(R.color.colorAccent);

        mBrokenLineTrendView.setBrokenLineTrendData(data);
        mBrokenLineTrendView.setOnItemClick(new BrokenLineTrendView.OnItemClick() {
            @Override
            public void onBrokenLinePointClick(int position, BrokenLineDimension dimension, List<String> xData) {
                Toast.makeText(MainActivity.this, xData.get(position) + dimension.remark +"." + dimension.mDatasList.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onXLinePointClick(int position, List<BrokenLineDimension> data, List<String> xData) {
                String msg = "";
                for (BrokenLineDimension dimension : data) {
                    String message = dimension.remark + ":"+ dimension.mDatasList.get(position) +",";
                    msg += message;
                }
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
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
