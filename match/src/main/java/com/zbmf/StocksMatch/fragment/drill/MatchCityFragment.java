package com.zbmf.StocksMatch.fragment.drill;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.zbmf.StocksMatch.R;
import com.zbmf.StocksMatch.adapter.MatchCityAdapter;
import com.zbmf.StocksMatch.bean.City;
import com.zbmf.StocksMatch.constatns.Constans;
import com.zbmf.StocksMatch.constatns.IntentKey;
import com.zbmf.StocksMatch.fragment.BaseFragment;
import com.zbmf.StocksMatch.listener.IMatchCityFragment;
import com.zbmf.StocksMatch.presenter.MatchCityPresenter;
import com.zbmf.worklibrary.pulltorefresh.PullToRefreshBase;
import com.zbmf.worklibrary.pulltorefresh.PullToRefreshListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xuhao on 2017/11/28.
 */

public class MatchCityFragment extends BaseFragment<MatchCityPresenter> implements IMatchCityFragment {
    @BindView(R.id.plv_match_list)
    PullToRefreshListView plvMatchList;
    @BindView(R.id.tv_name)
    TextView tvName;
    private int flag;
    private MatchCityAdapter adapter;

    public static MatchCityFragment newInstance(int flag) {
        MatchCityFragment fragment = new MatchCityFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(IntentKey.FLAG, flag);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_citylist_layout;
    }

    @Override
    protected MatchCityPresenter initPresent() {
        return new MatchCityPresenter(flag);
    }

    @Override
    protected void initView() {
        initProgressDialog(getString(R.string.loading_data));
        if (getArguments() != null) {
            flag = getArguments().getInt(IntentKey.FLAG);
            String name="";
            switch (flag){
                case Constans.CITY:
                    name="城市";
                    break;
                case Constans.SCHOOL:
                    name="学校";
                    break;
                case Constans.BUSINESS:
                    name="券商";
                    break;
            }
            tvName.setText(name);
        }
        plvMatchList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                presenter.getDatas();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                presenter.loadMore();
            }
        });
    }

    @Override
    protected void initData() {
        adapter = new MatchCityAdapter(getActivity());
        plvMatchList.setAdapter(adapter);
    }


    @Override
    public void RushCityList(List<City> matchBeans) {
        if (plvMatchList.isRefreshing()) {
            plvMatchList.onRefreshComplete();
        }
        adapter.setList(matchBeans);
    }

}
