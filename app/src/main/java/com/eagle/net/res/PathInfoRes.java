package com.eagle.net.res;

import com.eagle.entity.PathInfo;
import com.eagle.net.StringRes;

public abstract class PathInfoRes implements StringRes {
    @Override
    public void onRes(String res) {
        PathInfo info = new PathInfo();
        info.build(res);
        onRes(info);
    }
    public abstract void onRes( PathInfo info );
}
