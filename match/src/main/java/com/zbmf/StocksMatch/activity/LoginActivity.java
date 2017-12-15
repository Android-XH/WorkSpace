package com.zbmf.StocksMatch.activity;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;

import com.zbmf.StocksMatch.R;
import com.zbmf.StocksMatch.fragment.login.LoginFragment;
import com.zbmf.StocksMatch.fragment.login.RegisterFragment;
import com.zbmf.worklibrary.presenter.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xuhao on 2017/11/21.
 */

public class LoginActivity extends BaseActivity<BasePresenter> {

    @BindView(R.id.rg_login)
    RadioGroup rgLogin;

    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;
    @Override
    public BasePresenter initPresent() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected String initTitle() {
        return null;
    }

    @Override
    protected void initData(Bundle bundle) {
        initProgressDialog(getString(R.string.loading_login));
        setDefaultFragment();
        rgLogin.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.rb_register:
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction()
                                .setCustomAnimations(R.anim.slide_right_in,R.anim.slide_left_out);
                        if(registerFragment.isAdded()){
                            fragmentTransaction.hide(loginFragment).show(registerFragment).commit();
                        }else{
                            fragmentTransaction.add(R.id.fragment_login, registerFragment).commit();
                        }
                        break;
                    case R.id.rb_login:
                        FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction()
                                .setCustomAnimations(R.anim.slide_left_in,R.anim.slide_right_out);
                        if(loginFragment.isAdded()){
                            fragmentTransaction2.hide(registerFragment).show(loginFragment).commit();
                        }else{
                            fragmentTransaction2.add(R.id.fragment_login, loginFragment).commit();
                        }
                        break;
                }
            }
        });
    }

    private void setDefaultFragment(){
        if(loginFragment==null){
            loginFragment=LoginFragment.newInstance();
        }
        if(registerFragment==null){
            registerFragment=RegisterFragment.newInstance();
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_right_in,R.anim.slide_left_out);
        fragmentTransaction.add(R.id.fragment_login, loginFragment).commit();
    }
    @OnClick({R.id.iv_close, R.id.iv_weixin, R.id.iv_qq, R.id.iv_xinlang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.iv_weixin:

                break;
            case R.id.iv_qq:

                break;
            case R.id.iv_xinlang:

                break;
        }
    }
}
