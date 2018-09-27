package com.eagle.net;

import java.io.InputStream;
import java.util.Map;

public interface IHttpProxy extends Req{
    InputStream excute(String url, Map<String,String>param,Map<String,String>header);
    InputStream excute(String url, String param,Map<String,String>header);

}
