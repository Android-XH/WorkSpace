package com.zbmf.StocksMatch.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.zbmf.StocksMatch.R;
import com.zbmf.worklibrary.baseview.BaseView;
import com.zbmf.worklibrary.dialog.CustomProgressDialog;
import com.zbmf.worklibrary.presenter.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by xuhao on 2017/11/21.
 */
public abstract class BaseActivity<T extends BasePresenter> extends FragmentActivity implements BaseView, View.OnClickListener {
    protected T presenter;
    private CustomProgressDialog progressDialog;
    private Unbinder mUnBinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullscreen(true);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        initData(getIntent().getExtras());
        initTitle(initTitle());
        presenter = initPresent();
        if(presenter!=null){
            presenter.onStart(this);
            presenter.getDatas();
        }
        if(findViewById(R.id.imb_title_return)!=null){
            findViewById(R.id.imb_title_return).setOnClickListener(this);
        }
    }
    private void setFullscreen(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |=  bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    protected abstract int getLayout();
    protected abstract String initTitle();
    protected abstract void initData(Bundle bundle);
    protected abstract T initPresent();
    protected BasePresenter getPresenter(){
        return  presenter;
    }

    @Override
    public void showLoading() {
        ShowLoading();
    }

    @Override
    public void dissLoading() {
        DissLoading();
    }

    @Override
    public void showToastMsg(String msg) {
        showToast(msg);
    }

    public void ShowLoading() {
        if(progressDialog!=null&&!progressDialog.isShowing()){
            progressDialog.show();
        }
    }
    private void initTitle(String title){
        TextView textView=findViewById(R.id.tv_title);
        if(textView!=null&&title!=null){
            textView.setText(title);
        }
    }

    public void DissLoading() {
        if(progressDialog!=null&&progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
    public void initProgressDialog(String dialogMessage) {
        if (progressDialog == null) {
            progressDialog = CustomProgressDialog.createDialog(this);
        }
        progressDialog.setMessage(dialogMessage);
    }
    public void showToast(final String msg){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) {
            mUnBinder.unbind();
            mUnBinder=null;
        }
        if(presenter!=null){
            presenter.onDestroy();
            presenter=null;
        }
        if(progressDialog!=null){
            progressDialog=null;
        }
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}