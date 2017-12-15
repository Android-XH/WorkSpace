package com.zbmf.StocksMatch.fragment.login;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;

import com.zbmf.StocksMatch.MainActivity;
import com.zbmf.StocksMatch.R;
import com.zbmf.StocksMatch.bean.LoginUser;
import com.zbmf.StocksMatch.fragment.BaseFragment;
import com.zbmf.StocksMatch.listener.ILoginFragmentView;
import com.zbmf.StocksMatch.listener.IRegisterFragmentView;
import com.zbmf.StocksMatch.presenter.LoginFragmentPresenter;
import com.zbmf.worklibrary.util.Logx;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xuhao on 2017/12/7.
 */

public class RegisterFragment extends BaseFragment<LoginFragmentPresenter> implements IRegisterFragmentView {
    @BindView(R.id.et_user_name)
    TextInputEditText etUserName;
    @BindView(R.id.ti_user_name)
    TextInputLayout tiUserName;
    @BindView(R.id.et_user_pass)
    TextInputEditText etUserPass;
    @BindView(R.id.it_user_pass)
    TextInputLayout itUserPass;

    public static RegisterFragment newInstance(){
        RegisterFragment fragment=new RegisterFragment();
        return fragment;
    }
    @Override
    protected int getLayout() {
        return R.layout.fragment_register_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected LoginFragmentPresenter initPresent() {
        return null;
    }

    @OnClick(R.id.btn_reg)
    public void onViewClicked() {
        String username=etUserName.getText().toString();
        String pass= etUserPass.getText().toString();
        if(validateAccount(username)&&validatePassword(pass)){
            presenter.Login(etUserName.getText().toString(), etUserPass.getText().toString());
        }
    }
    /**
     * 验证用户名
     *
     * @param account
     * @return
     */
    private boolean validateAccount(String account) {
        if (TextUtils.isEmpty(account)) {
            showError(tiUserName, "用户名不能为空");
            return false;
        }
        clearError(tiUserName);
        return true;
    }

    /**
     * 验证密码
     *
     * @param password
     * @return
     */
    private boolean validatePassword(String password) {
        if (TextUtils.isEmpty(password)) {
            showError(itUserPass, "密码不能为空");
            return false;
        }

        if (password.length() < 6 || password.length() > 18) {
            showError(itUserPass, "密码长度为6-18位");
            return false;
        }
        clearError(itUserPass);
        return true;
    }
    /**
     * 显示错误提示，并获取焦点
     *
     * @param textInputLayout
     * @param error
     */
    private void showError(TextInputLayout textInputLayout, String error) {
        textInputLayout.setError(error);
        textInputLayout.getEditText().setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }
    private void clearError(TextInputLayout textInputLayout){
        textInputLayout.setErrorEnabled(false);
    }


    @Override
    public void onRegisterSucceed(LoginUser user) {
        Logx.e(user.getAuth_token());
        showToast("注册成功");
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
