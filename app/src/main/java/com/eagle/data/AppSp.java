package com.eagle.data;

import android.content.Context;
import android.content.SharedPreferences;

public class AppSp {
    private SharedPreferences sp;

    private static final String SP_NAME = "sdwan";
    private static AppSp ins;

    private AppSp() {
    }

    public static void setUp(Context context) {
        getIns().sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public static AppSp getIns() {
        if (ins == null) {
            synchronized (AppSp.class) {
                if (ins == null) {
                    ins = new AppSp();
                }
            }
        }
        return ins;
    }


}
