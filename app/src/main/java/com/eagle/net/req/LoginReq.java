package com.eagle.net.req;

import com.eagle.net.NetGet;
import com.eagle.net.ProxyConfig;
import com.eagle.net.StringRes;

/**
 * method:   GET
 url:           /v1/api/appuserauth?username=xxxx&passwd=xxxxx&forcelogin=0，其中passwd的值为密码的hex-md5值
 return（json格式）:
 {
 retcode: 0,                                                  // 0代表成功，1代表用户名或密码错误， 2代表用户流量已超标， 3代表该账户已登录
 desc: "ok",
 content: "xxxxxxx"                                     // retcode为0时代表sessionid，其他retcode时为空
 }
 */
public class LoginReq extends NetGet {
    String path = " /v1/api/appuserauth";

    public void login(final String email, final String pwd, final StringRes res){

       asynTask(new Runnable() {
           @Override
           public void run() {
               String url = ProxyConfig.getIns().getScheme()+HOST+path + "?username="+email+"&passwd="+pwd+"&forcelogin=0";
               excuteGet(url, res);
           }
       });
    }



}
