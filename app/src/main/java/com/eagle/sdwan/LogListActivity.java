package com.eagle.sdwan;

import android.os.Bundle;

public class LogListActivity extends BaseTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_list);
        setTitle(getString(R.string.main_more_info));
    }
}
