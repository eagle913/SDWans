package com.eagle.net;

public interface IResponse {
    int ERR_HOST = 1000;
    int ERR_TIME_OUT = ERR_HOST + 1;
    int ERR_EXCEPTION = ERR_HOST +2;
    void onConnectError(int errCode,String msg);
}
