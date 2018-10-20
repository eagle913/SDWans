package com.eagle.net.res;

import com.eagle.entity.Account;
import com.eagle.net.StringRes;

public abstract class LoginOutRes implements StringRes {
    @Override
    public void onRes(String res) {
        Account a = new Account();
        a.build(res);
        onRes(a);
    }
    public abstract void onRes(Account account);
}
