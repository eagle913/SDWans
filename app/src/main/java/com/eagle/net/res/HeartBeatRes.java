package com.eagle.net.res;

import com.eagle.entity.LiveInfo;
import com.eagle.net.StringRes;

public abstract class HeartBeatRes implements StringRes {
    @Override
    public void onRes(String res) {
        LiveInfo info = new LiveInfo();
        info.build(res);
        onRes(info);

    }
    public abstract void onRes( LiveInfo info );
}
