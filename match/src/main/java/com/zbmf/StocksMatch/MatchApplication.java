package com.zbmf.StocksMatch;

import android.app.Application;

import com.zbmf.StocksMatch.constatns.AppConfig;
import com.zbmf.worklibrary.util.Logx;
import com.zbmf.worklibrary.util.SharedpreferencesUtil;

/**
 * Created by xuhao on 2017/11/22.
 */

public class MatchApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Logx.init(AppConfig.IS_DEBUG);
        SharedpreferencesUtil.getInstance().initSharedUtil(this);
    }
}
