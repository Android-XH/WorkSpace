package com.zbmf.StocksMatch.model;

import com.zbmf.StocksMatch.api.Method;
import com.zbmf.StocksMatch.api.SendParam;
import com.zbmf.StocksMatch.bean.LoginUser;

import com.zbmf.StocksMatch.model.imode.ILoginMode;
import com.zbmf.StocksMatch.util.MatchSharedUtil;
import com.zbmf.worklibrary.model.CallBack;
import com.zbmf.worklibrary.util.GsonUtil;

/**
 * Created by xuhao on 2017/11/21.
 */

public class LoginMode extends BasePaSSMode implements ILoginMode {
    @Override
    public void login(String name, String pass,final CallBack callBack) {
        postSubscrube(Method.LOGIN, SendParam.getLoginMap(name, pass), new CallBack() {
            @Override
            public void onSuccess(Object o) {
                LoginUser loginUser= GsonUtil.parseData(o, LoginUser.class);
                if(loginUser.getStatus()){
                    MatchSharedUtil.saveUser(loginUser);
                    callBack.onSuccess(loginUser);
                }else{
                    callBack.onFail(loginUser.getErr().getMsg());
                }

            }

            @Override
            public void onFail(String msg) {
                callBack.onFail(msg);
            }
        });
    }
}
