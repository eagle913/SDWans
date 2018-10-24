package com.eagle.sdwan;

import android.support.multidex.MultiDexApplication;

import com.eagle.data.AppSp;

public class SDWApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        AppSp.setUp(this);

    }
}
