package com.eagle.utils;

import android.util.Log;

public class SDLog {
    private  static final String TAG = "SDLog";
    public static void  d(String tag,String msg){
        Log.d(TAG,tag+":"+msg);
    }

    public static void w(String tag, Throwable e) {
        Log.w(TAG,tag+":exception msg = "+e.getMessage());
    }

    public static void e(String tag, String e) {
        Log.e(TAG,tag+": "+e);
    }
}
