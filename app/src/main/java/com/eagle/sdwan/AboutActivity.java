package com.eagle.sdwan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class AboutActivity extends BaseTitleActivity {
    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle(getString(R.string.personal_about));
        mWebView = findViewById(R.id.webView);
        WebSettings set = mWebView.getSettings();
        set.setJavaScriptEnabled(true);
//        mWebView.loadUrl()
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.stopLoading();
            mWebView.clearFormData();
            mWebView.destroy();
        }
    }
}
