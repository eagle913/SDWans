package com.eagle.net.res;

import com.eagle.entity.IpTable;
import com.eagle.net.StringRes;

public abstract class IpTableRes implements StringRes {
    @Override
    final public void onRes(String res) {
        IpTable ipTable = new IpTable();
        ipTable.build(res);
        onRes(ipTable);
    }


    public abstract void onRes(IpTable res);
}
