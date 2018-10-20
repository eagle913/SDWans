package com.eagle.entity;

import com.eagle.net.res.Ret;

import org.json.JSONObject;

public class IpTable extends Ret {

    private String content;

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
}
