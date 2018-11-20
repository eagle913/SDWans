package com.eagle.sdwan;

import android.os.Bundle;

public class LogReportActivity extends BaseTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_report);
        setTitle(getString(R.string.personal_idea));
    }
}
