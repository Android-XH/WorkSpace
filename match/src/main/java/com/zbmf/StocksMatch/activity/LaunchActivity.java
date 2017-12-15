package com.zbmf.StocksMatch.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.zbmf.StocksMatch.MainActivity;
import com.zbmf.StocksMatch.R;
import com.zbmf.StocksMatch.constatns.SharedKey;
import com.zbmf.StocksMatch.listener.IlaunchView;
import com.zbmf.StocksMatch.presenter.LaunchPresenter;
import com.zbmf.worklibrary.util.SharedpreferencesUtil;

/**
 * Created by xuhao on 2017/11/24.
 */

public class LaunchActivity extends BaseActivity<LaunchPresenter> implements IlaunchView{
    @Override
    protected int getLayout() {
        return R.layout.activity_lanch_layout;
    }

    @Override
    protected String initTitle() {
        return null;
    }

    @Override
    protected void initData(Bundle bundle) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(SharedpreferencesUtil.getInstance().getString(SharedKey.AUTH_TOKEN,null)!=null){
                    Intent intent=new Intent(LaunchActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent=new Intent(LaunchActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
                finish();

            }
        },3000);
    }

    @Override
    protected LaunchPresenter initPresent() {
        return new LaunchPresenter();
    }

    @Override
    public void toLogin() {

    }
}
