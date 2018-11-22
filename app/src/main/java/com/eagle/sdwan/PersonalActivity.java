package com.eagle.sdwan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eagle.data.FwConfig;
import com.eagle.entity.Account;
import com.eagle.net.req.LogoutReq;
import com.eagle.net.res.LoginOutRes;

public class PersonalActivity extends BaseTitleActivity implements View.OnClickListener{

    private TextView txtCompany;
    private TextView txtName;
    private ImageView imgHead;
    private TextView txtCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        setTitle(getString(R.string.personal_text));
        txtCache = findViewById(R.id.txtCache);
        txtCompany = findViewById(R.id.txtCompany);
        txtName = findViewById(R.id.txtName);
        imgHead = findViewById(R.id.imgHeader);
        imgHead.setOnClickListener(this);
        findViewById(R.id.imgEdit).setOnClickListener(this);
        findViewById(R.id.linPwd).setOnClickListener(this);
        findViewById(R.id.linClean).setOnClickListener(this);
        findViewById(R.id.linAbout).setOnClickListener(this);
        findViewById(R.id.linIdea).setOnClickListener(this);
        findViewById(R.id.btnExit).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgHeader:
            case R.id.imgEdit:
                startActivity(new Intent(PersonalActivity.this, EditActivity.class));
                break;
            case R.id.linPwd:
                startActivity(new Intent(PersonalActivity.this,FixPwdActivity.class));
                break;
            case R.id.linClean:
                break;
            case R.id.linAbout:
                startActivity(new Intent(PersonalActivity.this,AboutActivity.class));
                break;
            case R.id.linIdea:
                startActivity(new Intent(PersonalActivity.this, LogReportActivity.class));
                break;
            case R.id.btnExit:
                loginOut();
                break;
        }

    }

    private void loginOut() {
        Account account = FwConfig.getIns().getAccount();
        if(account == null){
            //TODO
            exit();
        }else {
            LogoutReq req = new LogoutReq();
            req.logout(account.getAcc(), account.getSessId(), FwConfig.getIns().getTrafic(), new LoginOutRes() {
                @Override
                public void onRes(Account account) {
                    //
                    toast("账号已经登出");
                    exit();
                }

                @Override
                public void onConnectError(int errCode, String msg) {
                   exit();
                }
            });
        }
    }

    private void exit(){
        toast("应用退出待实现！");
    }
}
