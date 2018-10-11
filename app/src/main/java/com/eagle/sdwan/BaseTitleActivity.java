package com.eagle.sdwan;

import android.view.View;
import android.widget.TextView;

public abstract class BaseTitleActivity extends BaseActivity {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBarLeftClick();
            }
        });
    }

    protected void onBarLeftClick() {
        finish();
    }

    protected void setTitle(String title){
        ((TextView)findViewById(R.id.txtTitle)).setText(title);
    }
}
