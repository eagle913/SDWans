package com.eagle.net.res;

import com.eagle.entity.Account;
import com.eagle.net.StringRes;

public abstract class LoginRes implements StringRes{
    @Override
    final public void onRes(String res) {
        Account account = new Account();
        account.build(res);
        onRes(account);

    }
    public abstract void onRes(Account account);
}
