package com.eagle.entity;

import com.eagle.net.res.Ret;

import org.json.JSONObject;

public class LiveInfo extends Ret {
    private String content;
    private NetBw netBw;
    private IpTable ipTable;
    private StepNet stepNet;
    @Override
    protected void onJSONObj(JSONObject object) {
        setContent(getJsonString(object,"content"));
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NetBw getNetBw() {
        return netBw;
    }

    public void setNetBw(NetBw netBw) {
        this.netBw = netBw;
    }

    public IpTable getIpTable() {
        return ipTable;
    }

    public void setIpTable(IpTable ipTable) {
        this.ipTable = ipTable;
    }

    public StepNet getStepNet() {
        return stepNet;
    }

    public void setStepNet(StepNet stepNet) {
        this.stepNet = stepNet;
    }
}
