package com.eagle.net.res;

import com.eagle.net.StringRes;

public abstract class AbsSDRes<T extends Ret> implements StringRes {
    @Override
    final public void onRes(String res) {

    }

    public abstract void onRes(T t);
}
