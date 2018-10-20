package com.eagle.sdwan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eagle.data.FwConfig;
import com.eagle.entity.Account;
import com.eagle.entity.IpTable;
import com.eagle.entity.LiveInfo;
import com.eagle.entity.NetBw;
import com.eagle.entity.PathInfo;
import com.eagle.entity.StepNet;
import com.eagle.net.req.HeartBeatReq;
import com.eagle.net.req.IpTableReq;
import com.eagle.net.req.LoginReq;
import com.eagle.net.req.PathInfoReq;
import com.eagle.net.req.TunelDetailReq;
import com.eagle.net.req.TunelReq;
import com.eagle.net.res.HeartBeatRes;
import com.eagle.net.res.IpTableRes;
import com.eagle.net.res.LoginRes;
import com.eagle.net.res.PathInfoRes;
import com.eagle.net.res.StepNetBw;
import com.eagle.net.res.StepToNetRes;
import com.eagle.utils.UIUtil;

public class SDWLoginActivity extends BaseActivity {

    private EditText editAcc,editPwd;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdwlogin);
        UIUtil.statusBarLightMode(this);
        editAcc = findViewById(R.id.editAccount);
        editPwd = findViewById(R.id.editPwd);
        btnLogin =findViewById(R.id.btnLogin);
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateBtn();
            }
        };
        editAcc.addTextChangedListener(watcher);
        editPwd.addTextChangedListener(watcher);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
//                startActivity(new Intent(SDWLoginActivity.this,MainActivity.class));
            }
        });
        updateBtn();
    }

    private void updateBtn(){
        String acc = editAcc.getText().toString().trim();
        String pwd = editPwd.getText().toString().trim();
        if(acc.length()>0 && pwd.length()>0){
            btnLogin.setSelected(true);
            btnLogin.setEnabled(true);
        }else{
            btnLogin.setSelected(false);
            btnLogin.setEnabled(false);
        }
    }

    private void check(){
        String acc = editAcc.getText().toString().trim();
        String pwd = editPwd.getText().toString().trim();
//        if("".equals(acc) ||acc == null){
//            tost(getString(R.string.login_acc_empty));
//            return;
//        }
//
//        if("".equals(pwd) ||pwd == null){
//            tost(getString(R.string.login_pwd_empty));
//            return;
//
//        }
        attenptLogin(acc,pwd,false);
    }
    private void attenptLogin(final String acc,final String pwd,boolean isForce){
        LoginReq req = new LoginReq();
        req.setForce(isForce);
        req.login(acc, pwd, new LoginRes() {
            @Override
            public void onRes(Account account) {
                String ret = account.getRet();
                if(Account.RETCODE_ERR_DATA.equals(ret)){
//TODO TOS
                    tost(getString(R.string.common_net_err));
                }else if(Account.RETCODE_OK.equals(ret)){
                    account.setAcc(acc);
                    FwConfig.getIns().setAccount(account);
                    getIpTable(acc,account.getSessId());
                    loginSuccuss(acc,account.getSessId());

//                    startActivity(new Intent(SDWLoginActivity.this,MainActivity.class));
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
                            attenptLogin(acc,pwd,true);
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

    private void loginSuccuss(final String acc, String sessId) {

    }
    public void getIpTable(final String acc, final String sessId){
        IpTableReq req = new IpTableReq();
        req.getIptable(acc, sessId, new IpTableRes() {
            @Override
            public void onRes(IpTable res) {
                if(IpTable.RETCODE_OK.equals(res.getRet())){
                    // TODO Iptable getOk
                    onIpTable(res);
                    FwConfig.getIns().setIpTable(res);
                    stepToNet(acc,sessId);


                }else{
                    tost(getString(R.string.common_net_err));
                }
            }

            @Override
            public void onConnectError(int errCode, String msg) {
                //TODO 网络连接失败
                tost(getString(R.string.common_net_err));
            }
        });
    }

    public void onIpTable(IpTable res){
        tost("getIptable ok");
    }

    private  void stepToNet(final String acc, final String sessid){
        TunelReq req = new TunelReq();
        req.getTunels(acc, sessid, new StepToNetRes() {
            @Override
            public void onRes(StepNet stepNet) {
                if(StepNet.RETCODE_OK.equals(stepNet.getRet())){
                    FwConfig.getIns().setStepNet(stepNet);
                    onStepToNet(stepNet);
                    stepnetbw(acc,sessid);
                }else {
                    //TODO 网络连接失败
                    tost(getString(R.string.common_net_err));
                }
            }

            @Override
            public void onConnectError(int errCode, String msg) {
                //TODO 网络连接失败
                tost(getString(R.string.common_net_err));
            }
        });
    }
    private void onStepToNet(StepNet stepNet) {
        tost("getStepToNet ok");
    }

    private void stepnetbw(final String acc, final String sessid) {
        TunelDetailReq req = new TunelDetailReq();
        req.getTunelDetal(acc, sessid, new StepNetBw() {
            @Override
            public void onRes(NetBw bw) {
                if(NetBw.RETCODE_OK.equals(bw.getRet()) ){
                    FwConfig.getIns().setNetBw(bw);
                    onStepNetBw(bw);
                    getPathInfo(acc,sessid);
                }else{
                    //TODO 网络连接失败
                    tost(getString(R.string.common_net_err));
                }
            }

            @Override
            public void onConnectError(int errCode, String msg) {
                //TODO 网络连接失败
                tost(getString(R.string.common_net_err));
            }
        });
    }


    private void onStepNetBw(NetBw bw) {
        tost("StepNetBw ok");
    }

    private void getPathInfo(final String acc, final String sessid) {
        //TODO
        String isp = "移动";
        String province = "浙江省";
        PathInfoReq req = new PathInfoReq();
        req.getPathInfo(acc, sessid, province, isp, new PathInfoRes() {
            @Override
            public void onRes(PathInfo info) {
                if(PathInfo.RETCODE_OK.equals(info.getRet())){
                    FwConfig.getIns().setPathInfo(info);
                    onPathInfo(info);
                    startActivity(new Intent(SDWLoginActivity.this,MainActivity.class));
                    keepLive(acc,sessid);
                }else {
                    //TODO 网络连接失败
                    tost(getString(R.string.common_net_err));
                }
            }

            @Override
            public void onConnectError(int errCode, String msg) {
                //TODO 网络连接失败
                tost(getString(R.string.common_net_err));
            }
        });
    }



    private void onPathInfo(PathInfo info) {
        tost("PathInfo ok");
    }

    private void keepLive(String acc, String sessid) {
        String traffic = "988";
        HeartBeatReq req = new HeartBeatReq();
        req.heartbeat(acc, sessid, traffic, new HeartBeatRes() {
            @Override
            public void onRes(LiveInfo info) {
                onKeepLive(info);
            }

            @Override
            public void onConnectError(int errCode, String msg) {
                //TODO 网络连接失败
                tost(getString(R.string.common_net_err));
            }
        });

    }

    private void onKeepLive(LiveInfo info) {
        tost("onKeepLive ok");
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
        toast(msg);

    }

}
