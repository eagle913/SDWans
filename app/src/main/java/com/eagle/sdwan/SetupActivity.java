package com.eagle.sdwan;

import android.content.Intent;
import android.os.Bundle;

import com.eagle.utils.UIUtil;

public class SetupActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);


        setContentView(R.layout.activity_setup);
        UIUtil.statusBarLightMode(this);
        findViewById(R.id.imgLogo).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SetupActivity.this,SDWLoginActivity.class);
                startActivity(i);
                finish();
            }
        },2000);

    }
}
