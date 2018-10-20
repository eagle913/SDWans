package com.eagle.net.res;

import com.eagle.entity.NetBw;
import com.eagle.net.StringRes;

public abstract class StepNetBw implements StringRes {
    @Override
    public void onRes(String res) {
        NetBw bw = new NetBw();
        bw.build(res);
        onRes(bw);
    }
    public abstract void onRes( NetBw bw );
}
