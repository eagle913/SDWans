package com.eagle.sdwan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.eagle.function.PicSelector;

public class EditActivity extends BaseTitleActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        setTitle(getString(R.string.edit_info));

        findViewById(R.id.linAvter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PicSelector.fromAlbums(EditActivity.this);
            }
        });

        findViewById(R.id.linNick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PicSelector.takePhoto(EditActivity.this);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PicSelector.getIns().activityResult(requestCode,resultCode,data,this);
    }
}
