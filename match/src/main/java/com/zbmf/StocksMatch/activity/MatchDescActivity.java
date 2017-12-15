package com.zbmf.StocksMatch.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.zbmf.StocksMatch.R;
import com.zbmf.StocksMatch.bean.MatchBean;
import com.zbmf.StocksMatch.constatns.IntentKey;
import com.zbmf.StocksMatch.util.ShowActivity;
import com.zbmf.worklibrary.presenter.BasePresenter;
import com.zbmf.worklibrary.util.GetTime;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xuhao on 2017/12/12.
 */

public class MatchDescActivity extends BaseActivity {
    @BindView(R.id.tv_player_num)
    TextView tvPlayerNum;
    @BindView(R.id.tv_join_match)
    TextView tvJoinMatch;
    @BindView(R.id.match_desc)
    TextView matchDesc;
    @BindView(R.id.tv_match_jiangpin_msg)
    TextView tvMatchJiangpinMsg;
    @BindView(R.id.tv_apply_time)
    TextView tvApplyTime;
    @BindView(R.id.tv_match_time)
    TextView tvMatchTime;
    @BindView(R.id.init_money)
    TextView initMoney;

    private MatchBean matchBean;

    @Override
    protected int getLayout() {
        return R.layout.activity_match_desc_layout;
    }

    @Override
    protected String initTitle() {
        return matchBean!=null?matchBean.getTitle():getString(R.string.match_desc_title);
    }

    @Override
    protected void initData(Bundle bundle) {
        if (bundle != null) {
            matchBean = (MatchBean) bundle.getSerializable(IntentKey.MATCHBEAN);
            if (matchBean != null) {
                tvPlayerNum.setText(String.valueOf(matchBean.getPlayers()));
                matchDesc.setText(matchBean.getDesc());
                tvMatchJiangpinMsg.setText(!TextUtils.isEmpty(matchBean.getAward())? matchBean.getAward().replace("  ", "\n") : getString(R.string.match_desc_award));
                tvApplyTime.setText(String.format(getString(R.string.match_apply_date),matchBean.getStart_apply(),matchBean.getEnd_apply()));
                tvMatchTime.setText(String.format(getString(R.string.match_start_date),matchBean.getStart_at(),matchBean.getEnd_at()));
                initMoney.setText(String.format(getString(R.string.match_money),matchBean.getInit_money()));


                if(!GetTime.getTimeIsTrue(matchBean.getEnd_at())){//已结束
                    tvJoinMatch.setText(getString(R.string.match_is_over));
                    tvJoinMatch.setEnabled(false);
                }else if(!GetTime.getTimeIsTrue(matchBean.getEnd_apply())){
                    tvJoinMatch.setText(getString(R.string.match_apply_over));
                    tvJoinMatch.setEnabled(false);
                }else if(matchBean.getIs_match_player()){
                    tvJoinMatch.setText(getString(R.string.match_is_player));
                    tvJoinMatch.setEnabled(false);
                }else{
                    tvJoinMatch.setText(getString(R.string.match_join));
                    tvJoinMatch.setEnabled(true);
                }
            }
        }
    }

    @Override
    protected BasePresenter initPresent() {
        return null;
    }

    @OnClick(R.id.tv_join_match)
    public void onViewClicked() {
        Bundle bundle=new Bundle();
        bundle.putSerializable(IntentKey.MATCHBEAN,matchBean);
        ShowActivity.showActivity(this,bundle,MatchApplyActivity.class);
    }
}
