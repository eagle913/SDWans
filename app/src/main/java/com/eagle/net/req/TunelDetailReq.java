package com.eagle.net.req;

import com.eagle.net.NetGet;
import com.eagle.net.ProxyConfig;
import com.eagle.net.StringRes;

/**
 * stepnetbw 用户获取隧道详细信息
 * method: GET
 url:          /v1/api/appgetstepnetbw?username=xxxx&sessionid=xxxx
 return(json格式)  :
 {
 retcode: 0,
 desc: "ok",
 content: {
 "tunnel_153": {
 "peer_subnet": [
 "192.168.1.0/24",
 "192.168.2.0/23"
 ],
 "bw": "5Mbps",
 "alias": "二店-数据总部",
 "secret_key": "ok41xtqdrj3wpabl0fibsdoojd02gsft",
 "iscmpz": 1,
 "company_id": 3
 },
 "tunnel_157": {
 "peer_subnet": [
 "10.128.1.0/24",
 "10.128.2.0/24"
 ],
 "bw": "5Mbps",
 "alias": "二店 -生产总部",
 "secret_key": "ok41xtqdrj3wpabl0fibsdoojd02gsft",
 "iscmpz": 1,
 "company_id": 3
 }
 }
 }

 */
public class TunelDetailReq extends NetGet {
    String path = "/v1/api/appgetstepnetbw?";
    //TODO tunel id?
    public void getTunelDetal(final String userName, final String sessionId, final StringRes res){
        asynTask(new Runnable() {
            @Override
            public void run() {
                String url = ProxyConfig.getIns().getScheme()+HOST+path + "?username="+userName+"&sessionid="+sessionId;
                excuteGet(url, res);
            }
        });
    }
}
