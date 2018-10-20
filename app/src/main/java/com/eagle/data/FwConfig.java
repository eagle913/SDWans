package com.eagle.data;

import com.eagle.entity.Account;
import com.eagle.entity.IpTable;
import com.eagle.entity.NetBw;
import com.eagle.entity.PathInfo;
import com.eagle.entity.StepNet;

public class FwConfig {
    private static FwConfig ins;
    private FwConfig(){}

    private IpTable ipTable;
    private NetBw netBw;
    private StepNet stepNet;
    private PathInfo pathInfo;
    private Account account;

    private String trafic;

    public static FwConfig getIns() {
        if(ins == null){
            synchronized (FwConfig.class){
                if(ins == null){
                    ins = new FwConfig();
                }
            }
        }
        return ins;
    }


    public IpTable getIpTable() {
        return ipTable;
    }

    public void setIpTable(IpTable ipTable) {
        this.ipTable = ipTable;
    }

    public NetBw getNetBw() {
        return netBw;
    }

    public void setNetBw(NetBw netBw) {
        this.netBw = netBw;
    }

    public StepNet getStepNet() {
        return stepNet;
    }

    public void setStepNet(StepNet stepNet) {
        this.stepNet = stepNet;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public PathInfo getPathInfo() {
        return pathInfo;
    }

    public void setPathInfo(PathInfo pathInfo) {
        this.pathInfo = pathInfo;
    }

    public String getTrafic() {
        return trafic;
    }

    public void setTrafic(String trafic) {
        this.trafic = trafic;
    }
}
