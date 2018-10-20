package com.eagle.net.res;

import com.eagle.entity.StepNet;
import com.eagle.net.StringRes;

public abstract class StepToNetRes implements StringRes {
    @Override
    public void onRes(String res) {
        StepNet stepNet = new StepNet();
        stepNet.build(res);
        onRes(stepNet);
    }
    public abstract void onRes(StepNet stepNet);
}
