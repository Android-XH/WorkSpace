package com.zbmf.StocksMatch.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zbmf.StocksMatch.R;
import com.zbmf.StocksMatch.activity.LoginActivity;
import com.zbmf.StocksMatch.adapter.HomeMatchAdapter;
import com.zbmf.StocksMatch.bean.MatchBean;
import com.zbmf.StocksMatch.bean.User;
import com.zbmf.StocksMatch.constatns.SharedKey;
import com.zbmf.StocksMatch.listener.IMineView;
import com.zbmf.StocksMatch.presenter.MinePresenter;
import com.zbmf.StocksMatch.util.MatchSharedUtil;
import com.zbmf.StocksMatch.util.ShowActivity;
import com.zbmf.StocksMatch.view.GlideOptionsManager;
import com.zbmf.worklibrary.pulltorefresh.PullToRefreshBase;
import com.zbmf.worklibrary.pulltorefresh.PullToRefreshScrollView;
import com.zbmf.worklibrary.util.DoubleFromat;
import com.zbmf.worklibrary.util.SharedpreferencesUtil;
import com.zbmf.worklibrary.view.ListViewForScrollView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by xuhao on 2017/11/27.
 */

public class MineFragment extends BaseFragment<MinePresenter> implements IMineView {

    @BindView(R.id.imv_user_avatar)
    ImageView imvUserAvatar;
    @BindView(R.id.list_my_match)
    ListViewForScrollView listMyMatch;
    @BindView(R.id.mine_pull_to_refresh)
    PullToRefreshScrollView minePullToRefresh;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_mine_pay)
    TextView tvMinePay;
    @BindView(R.id.kf_layout_id)
    LinearLayout kfLayoutId;

    private HomeMatchAdapter homeMatchAdapter;

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine_layout;
    }

    @Override
    protected void initView() {
        minePullToRefresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                setLoadingVis(View.VISIBLE);
                presenter.getDatas();
            }
        });
        kfLayoutId.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        Glide.with(getActivity())
                .load(SharedpreferencesUtil.getInstance().getString(SharedKey.AVATAR, ""))
                .apply(GlideOptionsManager.getInstance().getRequestOptions())
                .into(imvUserAvatar);
        tvUserName.setText(SharedpreferencesUtil.getInstance().getString(SharedKey.NICK_NAME, ""));
        tvMinePay.setText("魔方宝" +
                DoubleFromat.getMoneyDouble(Double.valueOf(SharedpreferencesUtil.getInstance().getString(SharedKey.MPAY, "0.00")),2));
        homeMatchAdapter = new HomeMatchAdapter(getActivity());
        listMyMatch.setAdapter(homeMatchAdapter);
        listMyMatch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ShowActivity.showMatchDetail(getActivity(), homeMatchAdapter.getItem(i));
            }
        });
    }

    @Override
    protected MinePresenter initPresent() {
        return new MinePresenter();
    }


    @OnClick(R.id.tv_logout)
    public void onViewClicked() {
        MatchSharedUtil.clearUser();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void RushMatchList(List<MatchBean> matchBeans) {
        homeMatchAdapter.setList(matchBeans);
        setLoadingVis(View.GONE);
        listMyMatch.setVisibility(View.VISIBLE);
        kfLayoutId.setVisibility(View.VISIBLE);
    }

    @Override
    public void RushMineMessage(User user) {
        Glide.with(getActivity())
                .load(user.getAvatar())
                .apply(GlideOptionsManager.getInstance().getRequestOptions())
                .into(imvUserAvatar);
        tvUserName.setText(user.getNickname());
        tvMinePay.setText("魔方宝" + DoubleFromat.getMoneyDouble(user.getMpay(), 2));
        if (minePullToRefresh.isRefreshing()) {
            minePullToRefresh.onRefreshComplete();
        }
    }

    @Override
    public void onError() {
        if (minePullToRefresh.isRefreshing()) {
            minePullToRefresh.onRefreshComplete();
        }
        setLoadingVis(View.GONE);
        listMyMatch.setVisibility(View.VISIBLE);
        kfLayoutId.setVisibility(View.VISIBLE);
    }


    @OnClick({R.id.ll_stockgroup, R.id.ll_open, R.id.ll_cl, R.id.ll_stock_trader})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_stockgroup:
                PackageManager packageManager = getActivity().getPackageManager();
                Intent intent = packageManager.getLaunchIntentForPackage("com.zbmf.StockGroup");
                if(intent!=null){
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    this.startActivity(intent);
                }else{
                    showToast("请下载炒股圈子");
                }
                break;
            case R.id.ll_open:

                break;
            case R.id.ll_cl:

                break;
            case R.id.ll_stock_trader:

                break;
        }
    }
}
