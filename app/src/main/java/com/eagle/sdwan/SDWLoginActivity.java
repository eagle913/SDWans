package com.eagle.sdwan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.eagle.entity.Account;
import com.eagle.net.req.LoginReq;
import com.eagle.net.res.LoginRes;

public class SDWLoginActivity extends BaseActivity {

    private EditText editAcc,editPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdwlogin);
//        UIUtil.statusBarLightMode(this);
        editAcc = findViewById(R.id.editAccount);
        editPwd = findViewById(R.id.editPwd);

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
//                startActivity(new Intent(SDWLoginActivity.this,MainActivity.class));
            }
        });
    }

    private void check(){
        String acc = editAcc.getText().toString().trim();
        String pwd = editPwd.getText().toString().trim();
        if("".equals(acc) ||acc == null){
            tost(getString(R.string.login_acc_empty));
            return;
        }

        if("".equals(pwd) ||pwd == null){
            tost(getString(R.string.login_pwd_empty));
            return;

        }
        attenptLogin(acc,pwd);
    }
    private void attenptLogin(final String acc,final String pwd){
        LoginReq req = new LoginReq();
        req.login(acc, pwd, new LoginRes() {
            @Override
            public void onRes(Account account) {
                String ret = account.getRet();
                if(Account.RETCODE_ERR_DATA.equals(ret)){
//TODO TOST
                }else if(Account.RETCODE_OK.equals(ret)){
                    startActivity(new Intent(SDWLoginActivity.this,MainActivity.class));
                }else if(Account.RETCODE_EXIEST.equals(ret)){
                    dialog(getString(R.string.login_exist), getString(R.string.login_exit), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //TODO
                            finish();
                            dialogInterface.dismiss();
                        }
                    }, getString(R.string.login_force_login), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            attenptLogin(acc,pwd);
                            dialogInterface.dismiss();
                        }
                    });
                } else  if(Account.RETCODE_NOT_EXIST.equals(ret)){
                    tost(getString(R.string.login_not_exist));
                }
                else{
                    //TODO
                    tost(account.getDesc());
                }

            }

            @Override
            public void onConnectError(int errCode, String msg) {
                //TODO 网络连接失败
                tost(getString(R.string.common_net_err));
            }
        });
    }

    private void dialog(final String msg, final String text1, final DialogInterface.OnClickListener l1, final String text2, final DialogInterface.OnClickListener l2){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder b  =  new AlertDialog.Builder(SDWLoginActivity.this).setMessage(msg);
                if(text1 != null){
                    b.setPositiveButton(text1,l1);
                }
                if(text2 != null){
                    b.setNegativeButton(text2,l2);
                }
                b.setCancelable(false);
                b.create().show();
            }
        });

    }

    private void tost(final String msg){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SDWLoginActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

}
