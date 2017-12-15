package com.zbmf.StocksMatch.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zbmf.StocksMatch.R;
import com.zbmf.StocksMatch.adapter.HomeCityAdapter;
import com.zbmf.StocksMatch.adapter.HomeMatchAdapter;
import com.zbmf.StocksMatch.adapter.HomeSchoolAdapter;
import com.zbmf.StocksMatch.adapter.HomeTraderAdapter;
import com.zbmf.StocksMatch.bean.City;
import com.zbmf.StocksMatch.bean.MatchBean;
import com.zbmf.StocksMatch.bean.MatchSchool;
import com.zbmf.StocksMatch.bean.PhoneAd;
import com.zbmf.StocksMatch.bean.Traders;
import com.zbmf.StocksMatch.listener.IHomeView;
import com.zbmf.StocksMatch.presenter.HomePresenter;
import com.zbmf.StocksMatch.util.ShowActivity;
import com.zbmf.StocksMatch.view.ViewFactory;
import com.zbmf.worklibrary.pulltorefresh.PullToRefreshBase;
import com.zbmf.worklibrary.pulltorefresh.PullToRefreshScrollView;
import com.zbmf.worklibrary.view.CycleViewPager;
import com.zbmf.worklibrary.view.ListViewForScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xuhao on 2017/11/24.
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeView, CycleViewPager.ImageCycleViewListener<PhoneAd> {
    @BindView(R.id.tv_sh_num)
    TextView tvShNum;
    @BindView(R.id.tv_sh_yield_num)
    TextView tvShYieldNum;
    @BindView(R.id.tv_sh_yield)
    TextView tvShYield;
    @BindView(R.id.tv_sz_num)
    TextView tvSzNum;
    @BindView(R.id.tv_sz_yield_num)
    TextView tvSzYieldNum;
    @BindView(R.id.tv_sz_yield)
    TextView tvSzYield;
    @BindView(R.id.tv_cy_num)
    TextView tvCyNum;
    @BindView(R.id.tv_cy_yield_num)
    TextView tvCyYieldNum;
    @BindView(R.id.tv_cy_yield)
    TextView tvCyYield;
    @BindView(R.id.tv_create_match_num)
    TextView tvCreateMatchNum;
    @BindView(R.id.tv_player)
    TextView tvPlayer;
    @BindView(R.id.rv_school)
    RecyclerView rvSchool;
    @BindView(R.id.list_superme_match)
    ListViewForScrollView listSupermeMatch;
    @BindView(R.id.home_pull_scrollview)
    PullToRefreshScrollView homePullScrollview;
    @BindView(R.id.rv_trader)
    RecyclerView rvTrader;
    @BindView(R.id.rv_city)
    RecyclerView rvCity;
    @BindView(R.id.list_hot_match)
    ListViewForScrollView listHotMatch;

    private List<ImageView> views;
    private CycleViewPager<PhoneAd> cycleViewPager;

    private HomeMatchAdapter homeMatchAdapter, hotMatchAdapter;
    private HomeSchoolAdapter schoolAdapter;
    private HomeTraderAdapter traderAdapter;
    private HomeCityAdapter cityAdapter;
    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home_layout;
    }

    @Override
    protected HomePresenter initPresent() {
        return new HomePresenter();
    }

    @Override
    protected void initView() {
        initProgressDialog(getString(R.string.loading_data));
        homePullScrollview.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        homePullScrollview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                presenter.getDatas();
            }
        });

        if (cycleViewPager == null && getChildFragmentManager() != null) {
            cycleViewPager = (CycleViewPager) getChildFragmentManager().findFragmentById(R.id.home_cycleViewPage);
        }
        if (cycleViewPager != null) {
            cycleViewPager.setCycle(true);
            cycleViewPager.setWheel(true);
            cycleViewPager.setTime(2000);
            cycleViewPager.setIndicatorCenter();
        }
        listSupermeMatch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ShowActivity.showMatchDetail(getActivity(),homeMatchAdapter.getItem(i));
            }
        });
        listHotMatch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ShowActivity.showMatchDetail(getActivity(),hotMatchAdapter.getItem(i));
            }
        });
    }
    @Override
    protected void initData() {
        homeMatchAdapter = new HomeMatchAdapter(getActivity());
        listSupermeMatch.setAdapter(homeMatchAdapter);

        hotMatchAdapter=new HomeMatchAdapter(getActivity());
        listHotMatch.setAdapter(hotMatchAdapter);

        schoolAdapter = new HomeSchoolAdapter(getActivity());
        rvSchool.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvSchool.setAdapter(schoolAdapter);

        traderAdapter = new HomeTraderAdapter(getActivity());
        rvTrader.setLayoutManager(new GridLayoutManager(getActivity(), 3){
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvTrader.setAdapter(traderAdapter);

        cityAdapter=new HomeCityAdapter(getActivity());
        rvCity.setLayoutManager(new GridLayoutManager(getActivity(),3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }

            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        });
        rvCity.setAdapter(cityAdapter);

    }

    @Override
    public void RusnSupremeMatchAdapter(List<MatchBean> matchBeans) {
        homeMatchAdapter.setList(matchBeans);
    }

    @Override
    public void RushBannerImage(List<PhoneAd> imgList) {
        if (views == null) {
            views = new ArrayList<>();
        } else {
            views.clear();
        }
        for (int i = 0; i < imgList.size(); i++) {
            views.add(ViewFactory.getImageView(getActivity(), imgList.get(i).getPic_url()));
        }
        cycleViewPager.setData(views, imgList, this);
    }

    @Override
    public void RushMatchSchool(MatchSchool matchSchool) {
        tvCreateMatchNum.setText(String.format(getString(R.string.create_match), matchSchool.getMatch_num()));
        tvPlayer.setText(String.format(getString(R.string.match_player), matchSchool.getMatch_player()));
        schoolAdapter.refreshData(matchSchool.getSchools());
    }

    @Override
    public void RushTraderList(List<Traders> traders) {
        traderAdapter.refreshData(traders);
    }

    @Override
    public void RushHostMatch(List<MatchBean> matchBeans) {
        hotMatchAdapter.setList(matchBeans);
    }

    @Override
    public void RushCity(List<City> cityList) {
        cityAdapter.refreshData(cityList);
    }

    @Override
    public void RushScrollView() {
        if (homePullScrollview.isRefreshing()) {
            homePullScrollview.onRefreshComplete();
        }
    }

    @Override
    public void onImageClick(PhoneAd phoneAd, int position, View imageView) {
        //轮播图点击回调
        showToast("轮播图点击"+phoneAd.getTitle());
    }
    @OnClick({R.id.tv_trader_more, R.id.tv_city_more,R.id.tv_create})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_trader_more:
                showToast("点击了更多按钮");
                break;
            case R.id.tv_city_more:
                showToast("点击了更多按钮");
                break;
            case R.id.tv_create:
                showToast("点击了创建比赛按钮");
                break;
        }
    }
}
