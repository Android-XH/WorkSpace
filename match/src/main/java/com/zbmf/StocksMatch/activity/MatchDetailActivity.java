package com.zbmf.StocksMatch.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zbmf.StocksMatch.R;
import com.zbmf.StocksMatch.adapter.MatchDetailStockHoldAdapter;
import com.zbmf.StocksMatch.bean.MatchBean;
import com.zbmf.StocksMatch.bean.MatchInfo;
import com.zbmf.StocksMatch.bean.StockholdsBean;
import com.zbmf.StocksMatch.constatns.IntentKey;
import com.zbmf.StocksMatch.listener.IMatchDetailView;
import com.zbmf.StocksMatch.presenter.MatchDetailPresenter;
import com.zbmf.StocksMatch.util.ShowActivity;
import com.zbmf.StocksMatch.view.CustomCircleProgress;
import com.zbmf.worklibrary.pulltorefresh.PullToRefreshBase;
import com.zbmf.worklibrary.pulltorefresh.PullToRefreshScrollView;
import com.zbmf.worklibrary.util.DoubleFromat;
import com.zbmf.worklibrary.view.ListViewForScrollView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zbmf.worklibrary.util.DoubleFromat.getMoneyDouble;

/**
 * Created by xuhao on 2017/11/29.
 */

public class MatchDetailActivity extends BaseActivity<MatchDetailPresenter> implements IMatchDetailView {
    @BindView(R.id.tv_match_name)
    TextView tvMatchName;
    @BindView(R.id.tv_match_player)
    TextView tvMatchPlayer;
    @BindView(R.id.tv_match_date)
    TextView tvMatchDate;
    @BindView(R.id.tv_match_message)
    TextView tvMatchMessage;
    @BindView(R.id.imv_match_message_close)
    ImageView imvMatchMessageClose;
    @BindView(R.id.cp_yield)
    CustomCircleProgress cpYield;
    @BindView(R.id.tv_win_player)
    TextView tvWinPlayer;
    @BindView(R.id.tv_week_rank)
    TextView tvWeekRank;
    @BindView(R.id.tv_week_yield)
    TextView tvWeekYield;
    @BindView(R.id.tv_day_rank)
    TextView tvDayRank;
    @BindView(R.id.tv_day_yield)
    TextView tvDayYield;
    @BindView(R.id.tv_all_money)
    TextView tvAllMoney;
    @BindView(R.id.tv_stock_money)
    TextView tvStockMoney;
    @BindView(R.id.tv_can_use_money)
    TextView tvCanUseMoney;
    @BindView(R.id.tv_close_money)
    TextView tvCloseMoney;
    @BindView(R.id.tv_stock_num)
    TextView tvStockNum;
    @BindView(R.id.tv_money_yield)
    TextView tvMoneyYield;
    @BindView(R.id.cp_money)
    CustomCircleProgress cpMoney;
    @BindView(R.id.cp_stock)
    CustomCircleProgress cpStock;
    @BindView(R.id.cp_close_money)
    CustomCircleProgress cpCloseMoney;
    @BindView(R.id.cp_can_use_money)
    CustomCircleProgress cpCanUseMoney;
    @BindView(R.id.simulate_hold_list)
    ListViewForScrollView simulateHoldList;
    @BindView(R.id.pull_scrollview)
    PullToRefreshScrollView pullScrollview;
    @BindView(R.id.btn_all_hold)
    TextView btnAllHold;
    private MatchBean matchBean;

    private MatchDetailStockHoldAdapter stockHoldAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_match_detail;
    }

    @Override
    protected String initTitle() {
        return getString(R.string.match);
    }

    @Override
    protected void initData(Bundle bundle) {
        if (bundle != null) {
            matchBean = (MatchBean) bundle.getSerializable(IntentKey.MATCHBEAN);
        }
        tvMatchName.setText(matchBean.getTitle());
        tvMatchPlayer.setText(String.format(getString(R.string.match_detail_players), matchBean.getPlayers()));
        tvMatchDate.setText(String.format(getString(R.string.match_detail_date), matchBean.getStart_at(), matchBean.getEnd_at()));
        tvMatchMessage.setText("中国19大召开后，资金股票将要大涨10个板！");
        pullScrollview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                presenter.getDatas();
            }
        });
        stockHoldAdapter = new MatchDetailStockHoldAdapter(this);
        simulateHoldList.setAdapter(stockHoldAdapter);
    }

    @Override
    protected MatchDetailPresenter initPresent() {
        return new MatchDetailPresenter(String.valueOf(matchBean.getId()));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void RushMatchDetail(MatchInfo matchInfo) {
        tvWinPlayer.setText(String.format(getString(R.string.match_win_player), matchInfo.getCount_players(), matchInfo.getCount_players() - matchInfo.getTotal_rank()));
        tvWeekYield.setTextColor(matchInfo.getWeek_yield() > 0 ? getColor(R.color.red) : getColor(R.color.green));
        tvWeekYield.setText(String.format("%+.2f%%", matchInfo.getWeek_yield() * 100));
        tvWeekRank.setText(String.format(getString(R.string.match_detail_rank), matchInfo.getWeek_rank()));
        tvDayYield.setTextColor(matchInfo.getDay_yield() > 0 ? getColor(R.color.red) : getColor(R.color.green));
        tvDayYield.setText(String.format("%+.2f%%", matchInfo.getDay_yield() * 100));
        tvDayRank.setText(String.format(getString(R.string.match_detail_rank), matchInfo.getDay_rank()));

        tvAllMoney.setText(getMoneyDouble(matchInfo.getTotal(), 2));
        tvStockMoney.setText(getMoneyDouble(matchInfo.getStocks_value(), 2));

        cpYield.setContentText(String.format(getString(R.string.match_detail_rank), matchInfo.getTotal_rank()));
        cpYield.runAnimat((float) matchInfo.getYield() * 100);

        tvCanUseMoney.setText(DoubleFromat.getStockDouble(matchInfo.getMoneyunfrozen(), 2));
        cpCanUseMoney.runAnimat((float) (matchInfo.getMoneyunfrozen() / matchInfo.getTotal()) * 100);
        tvCloseMoney.setText(DoubleFromat.getStockDouble(matchInfo.getFrozen(), 2));
        cpCloseMoney.runAnimat((float) (matchInfo.getFrozen() / matchInfo.getTotal()) * 100);
        tvStockNum.setText(String.format("%.2f%%", matchInfo.getPosition() * 100));
        cpStock.runAnimat((float) matchInfo.getPosition() * 100);
        tvMoneyYield.setText(String.format("%+.2f%%", matchInfo.getWeek_velocity() * 100));
        cpMoney.runAnimat((float) matchInfo.getWeek_velocity() * 100);

        if (pullScrollview.isRefreshing()) {
            pullScrollview.onRefreshComplete();
        }
    }

    @Override
    public void RushMatchHold(List<StockholdsBean> stockholdsBeans) {
        btnAllHold.setVisibility(stockholdsBeans.size()>0?View.VISIBLE: View.GONE);
        stockHoldAdapter.setList(stockholdsBeans);
    }

    @OnClick({R.id.tv_hold, R.id.tv_buy,
            R.id.tv_sell, R.id.tv_cause,
            R.id.tv_qure, R.id.tv_cup, R.id.tv_share,
            R.id.tv_reload, R.id.tv_share_friend,
            R.id.btn_all_hold, R.id.tv_match_rank})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_hold:
                break;
            case R.id.tv_buy:
                break;
            case R.id.tv_sell:
                break;
            case R.id.tv_cause:
                break;
            case R.id.tv_qure:
                break;
            case R.id.tv_cup:
                break;
            case R.id.tv_share:
                break;
            case R.id.tv_reload:
                break;
            case R.id.tv_share_friend:
                break;
            case R.id.btn_all_hold:
                break;
            case R.id.tv_match_rank:
                Bundle bundle = new Bundle();
                bundle.putString(IntentKey.MATCH_ID, String.valueOf(matchBean.getId()));
                ShowActivity.showActivity(this, bundle, MatchRankActivity.class);
                break;
        }
    }
}
