package com.zbmf.StocksMatch.listener;

import com.zbmf.StocksMatch.bean.LoginUser;
import com.zbmf.worklibrary.baseview.BaseView;


/**
 * Created by xuhao on 2017/11/21.
 */

public interface IRegisterFragmentView extends BaseView {
    void onRegisterSucceed(LoginUser user);  //注册成功
}
