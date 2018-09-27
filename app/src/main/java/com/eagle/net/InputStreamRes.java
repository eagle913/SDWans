package com.eagle.net;

import java.io.InputStream;

public interface InputStreamRes extends IResponse {
    void onRes(InputStream stream);
}
