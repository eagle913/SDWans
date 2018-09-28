package com.eagle.net.req;

import com.eagle.net.NetGet;
import com.eagle.net.ProxyConfig;
import com.eagle.net.StringRes;

/**
 * stepToNet用于寻找隧道
 * method: GET
 url:          /v1/api/appgetsteptonet?username=xxxx&sessionid=xxxx
 return(json格式)  :
 {
 retcode: 0,
 desc: "ok",
 content: {
 "STEP01R1201808060000000000000004": [
 "192.168.1.0/24"
 ],
 "STEP01R1201808060000000000000005": [
 "192.168.2.0/24"
 ],
 "STEP01R1201808060000000000000006": [
 "192.168.3.0/24"
 ]
 }
 }

 */
public class TunelReq extends NetGet {
    String path = "/v1/api/appgetsteptonet";
    public void getTunels(final String userName, final String sessionId, final StringRes res){
        asynTask(new Runnable() {
            @Override
            public void run() {
                String url = ProxyConfig.getIns().getScheme()+HOST+path + "?username="+userName+"&sessionid="+sessionId;
                excuteGet(url, res);
            }
        });
    }

}
