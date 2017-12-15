package com.zbmf.StocksMatch.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zbmf.StocksMatch.R;
import com.zbmf.StocksMatch.activity.MatchRankActivity;
import com.zbmf.StocksMatch.adapter.MatchDetailStockHoldAdapter;
import com.zbmf.StocksMatch.bean.MatchInfo;
import com.zbmf.StocksMatch.bean.StockholdsBean;
import com.zbmf.StocksMatch.constatns.Constans;
import com.zbmf.StocksMatch.constatns.IntentKey;
import com.zbmf.StocksMatch.listener.IDrillFragment;
import com.zbmf.StocksMatch.presenter.DrillPresenter;
import com.zbmf.StocksMatch.util.ShowActivity;
import com.zbmf.StocksMatch.view.CustomMyProgress;
import com.zbmf.StocksMatch.view.MyIncreaseView;
import com.zbmf.worklibrary.util.DoubleFromat;
import com.zbmf.worklibrary.view.ListViewForScrollView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 训练
 * Created by xuhao on 2017/11/27.
 */

public class DrillFragment extends BaseFragment<DrillPresenter> implements IDrillFragment {
    @BindView(R.id.tv_all_num)
    TextView tvAllNum;
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.tv_all_asset)
    TextView tvAllAsset;
    @BindView(R.id.tv_can_use)
    TextView tvCanUse;
    @BindView(R.id.tv_profit)
    TextView tvProfit;
    @BindView(R.id.tv_day_yield)
    TextView tvDayYield;
    @BindView(R.id.custom_day_progress)
    CustomMyProgress customDayProgress;
    @BindView(R.id.tv_week_yield)
    TextView tvWeekYield;
    @BindView(R.id.custom_week_progress)
    CustomMyProgress customWeekProgress;
    @BindView(R.id.btn_reset)
    TextView btnReset;
    @BindView(R.id.simulate_hold_list)
    ListViewForScrollView simulateHoldList;
    @BindView(R.id.tv_day_reached)
    MyIncreaseView tvDayReached;
    @BindView(R.id.tv_week_reached)
    MyIncreaseView tvWeekReached;
    @BindView(R.id.btn_all_hold)
    TextView btnAllHold;

    private MatchDetailStockHoldAdapter adapter;

    public static DrillFragment newInstance() {
        DrillFragment fragment = new DrillFragment();
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_drill_layout;
    }

    @Override
    protected DrillPresenter initPresent() {
        return new DrillPresenter(Constans.MATCH_ID);
    }

    @Override
    protected void initView() {
        setTitleMessage(getString(R.string.drill));
    }

    @Override
    protected void initData() {
        adapter = new MatchDetailStockHoldAdapter(getActivity());
        simulateHoldList.setAdapter(adapter);
    }

    @OnClick({R.id.tv_hold, R.id.tv_buy,
            R.id.tv_sell_id, R.id.tv_match_trusts, R.id.tv_log_list, R.id.tv_record, R.id.btn_reset, R.id.btn_all_hold
            , R.id.ll_stock_chat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_hold:
                break;
            case R.id.tv_buy:
                break;
            case R.id.tv_sell_id:
                break;
            case R.id.tv_match_trusts:
                break;
            case R.id.tv_log_list:
                break;
            case R.id.tv_record:
                break;
            case R.id.btn_reset:
                break;
            case R.id.btn_all_hold:
                break;
            case R.id.ll_stock_chat:
                Bundle bundle = new Bundle();
                bundle.putString(IntentKey.MATCH_ID,Constans.MATCH_ID);
                ShowActivity.showActivity(getActivity(), bundle, MatchRankActivity.class);
                break;
        }
    }

    private double paynum;

    @Override
    public void rushMatchBean(MatchInfo matchInfo) {
        tvAllNum.setText(matchInfo.getCount_players() + "");
        if (matchInfo.getYield() >= 0) {
            tvProfit.setText("+" + DoubleFromat.getStockDouble(matchInfo.getYield() * 100, 2) + "%");
            btnReset.setVisibility(View.GONE);
        } else {
            btnReset.setVisibility(View.VISIBLE);
            tvProfit.setText(DoubleFromat.getStockDouble(matchInfo.getYield() * 100, 2) + "%");
        }
        paynum = matchInfo.getPaynum();
        tvAll.setText(matchInfo.getCount_players() + "");
        tvAllAsset.setText(DoubleFromat.getStockDouble(matchInfo.getTotal(), 2));
        tvCanUse.setText(DoubleFromat.getStockDouble(matchInfo.getMoneyunfrozen(), 2));
        tvProfit.setTextColor(matchInfo.getYield() >= 0 ? getResources().getColor(R.color.red) : getResources().getColor(R.color.green));
        tvWeekYield.setTextColor(matchInfo.getWeek_yield() >= 0 ? getResources().getColor(R.color.red) : getResources().getColor(R.color.green));
        tvDayYield.setTextColor(matchInfo.getDay_yield() >= 0 ? getResources().getColor(R.color.red) : getResources().getColor(R.color.green));
        tvWeekYield.setText(String.format("%+.2f%%", matchInfo.getWeek_yield() * 100));
        tvDayYield.setText(String.format("%+.2f%%", matchInfo.getDay_yield() * 100));
        int maxDayNum = matchInfo.getCount_players(), maxWeekNum = matchInfo.getCount_players();
        int currDayReachedNum = matchInfo.getDay_rank() > 0 ? maxDayNum - matchInfo.getDay_rank() : 0;
        int currWeekReachedNum = matchInfo.getWeek_rank() > 0 ? maxDayNum - matchInfo.getWeek_rank() : 0;
        tvDayReached.setMax(currDayReachedNum);
        tvWeekReached.setMax(currWeekReachedNum);
        tvDayReached.increaseBarBrother();
        tvWeekReached.increaseBarBrother();
        customDayProgress.setMax(maxDayNum);
        customDayProgress.setInitProgress(currDayReachedNum);
        customDayProgress.displayNiuAnim();
        customWeekProgress.setMax(maxWeekNum);
        customWeekProgress.setInitProgress(currWeekReachedNum);
        customWeekProgress.displayNiuAnim();
    }

    @Override
    public void rushHold(List<StockholdsBean> stockholdsBeans) {
        btnAllHold.setVisibility(stockholdsBeans.size() > 0 ? View.VISIBLE : View.GONE);
        adapter.setList(stockholdsBeans);
    }

}
