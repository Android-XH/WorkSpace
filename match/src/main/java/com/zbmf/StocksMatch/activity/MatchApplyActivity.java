package com.zbmf.StocksMatch.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zbmf.StocksMatch.R;
import com.zbmf.StocksMatch.api.ParamsKey;
import com.zbmf.StocksMatch.api.SendParam;
import com.zbmf.StocksMatch.bean.MatchBean;
import com.zbmf.StocksMatch.constatns.IntentKey;
import com.zbmf.StocksMatch.listener.IApplyMatchView;
import com.zbmf.StocksMatch.presenter.ApplyMatchPresenter;
import com.zbmf.StocksMatch.util.EditUtil;
import com.zbmf.StocksMatch.util.MatchSharedUtil;
import com.zbmf.StocksMatch.util.ShowActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xuhao on 2017/12/12.
 */

public class MatchApplyActivity extends BaseActivity<ApplyMatchPresenter> implements IApplyMatchView {
    @BindView(R.id.et_user_name)
    TextInputEditText etUserName;
    @BindView(R.id.ti_user_name)
    TextInputLayout tiUserName;
    @BindView(R.id.et_true_name)
    TextInputEditText etTrueName;
    @BindView(R.id.it_true_name)
    TextInputLayout itTrueName;
    @BindView(R.id.et_code)
    TextInputEditText etCode;
    @BindView(R.id.it_code)
    TextInputLayout itCode;
    @BindView(R.id.ll_get_code)
    LinearLayout llGetCode;
    @BindView(R.id.et_user_phone)
    TextInputEditText etUserPhone;
    @BindView(R.id.it_user_phone)
    TextInputLayout itUserPhone;
    @BindView(R.id.tv_question)
    TextView tvQuestion;
    @BindView(R.id.et_user_answer)
    TextInputEditText etUserAnswer;
    @BindView(R.id.ti_user_answer)
    TextInputLayout tiUserAnswer;
    @BindView(R.id.ll_question)
    LinearLayout llQuestion;
    @BindView(R.id.btn_code)
    Button btnCode;
    @BindView(R.id.btn_apply)
    Button btnApply;
    private MatchBean matchBean;

    @Override
    protected int getLayout() {
        return R.layout.activity_apply_match;
    }

    @Override
    protected String initTitle() {
        return matchBean != null ? matchBean.getTitle() : getString(R.string.match_join);
    }

    @Override
    protected void initData(Bundle bundle) {
        if (bundle != null) {
            matchBean = (MatchBean) bundle.getSerializable(IntentKey.MATCHBEAN);
            //是否需要手机号码和验证 1都要  2不要验证码 3都不要
            switch (matchBean.getMatch_status()) {
                case 1:
                    itUserPhone.setVisibility(View.VISIBLE);
                    llGetCode.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    itUserPhone.setVisibility(View.VISIBLE);
                    break;
            }
        }
        etUserName.setText(MatchSharedUtil.UserName());
        etUserPhone.setText(MatchSharedUtil.UserPhone());
    }

    @Override
    protected ApplyMatchPresenter initPresent() {
        return new ApplyMatchPresenter(String.valueOf(matchBean != null ? matchBean.getId() : 0));
    }

    @Override
    public void onSucceed() {
        showToast(getString(R.string.match_join_success));
        matchBean.setIs_match_player(1);
        ShowActivity.showMatchDetail(this, matchBean);
    }

    @OnClick({R.id.btn_code, R.id.btn_apply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_code:

                break;
            case R.id.btn_apply:
                Map<String,String>parma= new HashMap<>();
                if(itUserPhone.getVisibility()==View.VISIBLE){
                    String userPhone=etUserPhone.getText().toString();
                    if(EditUtil.isEmpty(itUserPhone,userPhone,getString(R.string.err_input_phone))){
                        return;
                    }
                    parma.put(ParamsKey.MOBILE,userPhone);
                }
                if(itTrueName.getVisibility()==View.VISIBLE){
                    String trueName=etTrueName.getText().toString();
                    if(EditUtil.isEmpty(itTrueName,trueName,getString(R.string.err_input_true_name))){
                        return;
                    }
                    parma.put(ParamsKey.TRUENAME,trueName);
                }
                if(llGetCode.getVisibility()==View.VISIBLE){
                    String code=etCode.getText().toString();
                    if(EditUtil.isEmpty(itCode,code,getString(R.string.err_input_true_name))){
                        return;
                    }
                    parma.put(ParamsKey.CODE,code);
                }
                presenter.AppleMatchMode(parma);
                break;
        }
    }

}
