package com.eagle.entity;

import com.eagle.net.res.Ret;

import org.json.JSONObject;

public class Account extends Ret{
    public static final String RETCODE_EXIEST = "3";
    public static final String RETCODE_NOT_EXIST = "1";
    String sessId = "";

    public String getSessId() {
        return sessId;
    }

    public void setSessId(String sessId) {
        this.sessId = sessId;
    }

    @Override
    protected void onJSONObj(JSONObject object) {
        setSessId(getJsonString(object,"content"));
    }

    @Override
    public String toString() {
        return "Account{" +
                "sessId='" + sessId + "\' " + super.toString() +
                '}';
    }
}
