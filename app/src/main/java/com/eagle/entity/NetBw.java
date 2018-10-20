package com.eagle.entity;

import com.eagle.net.res.Ret;

import org.json.JSONObject;

import java.util.List;

public class NetBw extends Ret {
    private List<Tunnel> tunnels;
    private String content;
    @Override
    protected void onJSONObj(JSONObject object) {
        setContent(getJsonString(object,"content"));
    }

    public List<Tunnel> getTunnels() {
        return tunnels;
    }

    public void setTunnels(List<Tunnel> tunnels) {
        this.tunnels = tunnels;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
