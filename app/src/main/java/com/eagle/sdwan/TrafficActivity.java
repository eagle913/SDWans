package com.eagle.sdwan;

import android.os.Bundle;

public class TrafficActivity extends BaseTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic);
        setTitle(getString(R.string.traffic_title));
    }
}
