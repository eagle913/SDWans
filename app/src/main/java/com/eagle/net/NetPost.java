package com.eagle.net;

import java.net.URLConnection;
import java.util.Map;

public class NetPost extends NetProxy {
    @Override
    protected String buildUrl(String url, Map<String, String> params) {
        return null;
    }

    @Override
    protected void setMethed(URLConnection connection) {

    }

    @Override
    protected void setRequestProperty(URLConnection connection, Map<String, String> headers) {

    }

    @Override
    protected void writeReqData(URLConnection connection) {
        super.writeReqData(connection);
    }
}
