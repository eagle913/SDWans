package com.eagle.net.req;

import com.eagle.net.NetGet;
import com.eagle.net.ProxyConfig;
import com.eagle.net.StringRes;

/**
 * 获取路径信息用户建立隧道：
 method: GET
 url:          /v1/api/appgetpathinfo?username=xxxx&sessionid=xxxx&provience=xxxx&isp=xxxx               // 其中provience为用户当前位置所在省份，isp为用户所在运营商
 return(json格式)  :
 {
 retcode: 0,
 desc: "ok",
 content: {
 "tunnel_84:STEP01L1201804250000000000000008": [
 [
 "interface:eth0.1",
 "101.132.101.81:8819:ALIBABA",
 "1.203.163.162:8819:CT"
 ],
 [
 "interface:eth0.1",
 "1.203.163.162:8819:CT"
 ]
 ],
 "tunnel_85:STEP01L1201804250000000000000009": [
 [
 "interface:eth0.1",
 "1.203.163.166:8819:CT"
 ]
 ]
 }
 }

 */
public class PathInfoReq extends NetGet {
    String path = "/v1/api/appgetpathinfo";

    public void getPathInfo(final String userName, final String sessionId, final String provience, final String isp, final StringRes res){
        asynTask(new Runnable() {
            @Override
            public void run() {
                String url = ProxyConfig.getIns().getScheme()+HOST+path + "?username="+userName+"&sessionid="+sessionId+"&provience="  + provience + "&isp=" + isp;
                excuteGet(url, res);
            }
        });
    }

}
