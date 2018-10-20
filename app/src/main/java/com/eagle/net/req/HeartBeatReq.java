package com.eagle.net.req;

import com.eagle.net.NetPost;
import com.eagle.net.ProxyConfig;
import com.eagle.net.StringRes;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 *
 * 用户心跳信息：
 method: POST
 url:         /v1/api/userkeepalive?username=xxx&sessionid=xxxx&traffic=xxxx
 post数据:
 {
 "iptable": "xxxxx",                                // 其中xxxxx为三个文件各自的md5值
 "steptonet": "xxxxx",
 "stepnetbw": "xxxxx"
 }
 return（json格式）:
 {
 retcode: 0,                                                  // 0代表成功, 1代表username或者sessionid错误。
 desc: "ok",
 content: {                                                    // content根据上面的md5进行判断，如果有变化为该文件新的内容，如果没有变化则为null。其文件格式为三个接口对应的content格式。
 // retcode为非0的时候该字段为空。
 "iptable":  ["10.0.1.0/24", "10.0.2.0/24", "10.0.3.0/25"],
 "steptonet": null,
 "stepnetbw": null
 }
 }

 */
public class HeartBeatReq extends NetPost {
    private String path = "/v1/api/userkeepalive";

    public void heartbeat(final String userName, final String sessionId, final String traffic, final StringRes res){
        response = res;
        asynTask(new Runnable() {
            @Override
            public void run() {
                //TODO POST 尝试参数在url后面的post
                String url = ProxyConfig.getIns().getScheme()+HOST+path +"?username="+userName+"&sessionid="+sessionId+"&traffic="  + traffic;
                JSONObject object = new JSONObject();
                //TODO md5
                String param = "";
                try {
                    object.put("iptable","");
                    object.put("steptonet","");
                    object.put("stepnetbw","");
                    param = object.toString();
                }catch (JSONException E){

                }

                excute(url, param,null);
            }
        });
    }
}
