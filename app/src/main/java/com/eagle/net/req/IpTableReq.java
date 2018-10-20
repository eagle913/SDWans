package com.eagle.net.req;

import com.eagle.net.NetGet;
import com.eagle.net.ProxyConfig;
import com.eagle.net.StringRes;

/**
 *method: GET
 url:          /v1/api/appgetiptable?username=xxxx&sessionid=xxxx
 return(json格式)  :
 {
 retcode: 0,
 desc: "ok",
 content: ["10.0.1.0/24", "10.0.2.0/24", "10.0.3.0/25"]
 }

 */

public class IpTableReq extends NetGet {
    String path = "/v1/api/appgetiptable";
    public void getIptable(final String userName, final String sessionId, final StringRes res) {
        asynTask(new Runnable() {
        @Override
        public void run () {
            String url = ProxyConfig.getIns().getScheme() + HOST+path + "?username=" + userName + "&sessionid=" + sessionId + "&forcelogin=0";
            excuteGet(url, res);
        }
    });
    }
}
