package com.eagle.net;

public class ProxyConfig {
    public static final String SCHEME_HTTP = "http://";
    public static final String SCHEME_HTTPS = "https://";
    public static final int THREAD_COUUT = 4;

    private String scheme = SCHEME_HTTP;
    private static ProxyConfig ins;

    private ProxyConfig() {
    }

    public static ProxyConfig getIns() {
        if (ins == null) {
            synchronized (ProxyConfig.class) {
                if (ins == null) {
                    ins = new ProxyConfig();
                }
            }
        }
        return ins;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }
}
