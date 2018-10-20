package com.eagle.net.req;

import com.eagle.net.NetGet;
import com.eagle.net.ProxyConfig;
import com.eagle.net.StringRes;

/**
 * method: GET
 url:         /v1/api/applogoff?username=xxx&sessionid=xxxx&traffic=xxxx
 return（json格式）:
 {
 retcode: 0,                                                  // 0代表成功
 desc: "ok",
 content: ""                                                  //目前该字段为空
 }

 */
public class LogoutReq extends NetGet {
    String path = "/v1/api/applogoff";

    public void logout(final String email, final String sessionId, final String traffic, final StringRes res){

        asynTask(new Runnable() {
            @Override
            public void run() {
                String url = ProxyConfig.getIns().getScheme()+HOST+path + "?username="+email+"&sessionid="+sessionId+"&traffic="  + traffic;
                excuteGet(url, res);
            }
        });
    }
}
