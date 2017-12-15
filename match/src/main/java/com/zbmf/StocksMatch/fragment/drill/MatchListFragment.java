package com.zbmf.StocksMatch.fragment.drill;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zbmf.StocksMatch.R;
import com.zbmf.StocksMatch.activity.MatchDetailActivity;
import com.zbmf.StocksMatch.adapter.HomeMatchAdapter;
import com.zbmf.StocksMatch.bean.MatchBean;
import com.zbmf.StocksMatch.constatns.IntentKey;
import com.zbmf.StocksMatch.fragment.BaseFragment;
import com.zbmf.StocksMatch.listener.IMatchFragment;
import com.zbmf.StocksMatch.presenter.MatchFaragmentPresenter;
import com.zbmf.StocksMatch.util.ShowActivity;
import com.zbmf.worklibrary.pulltorefresh.PullToRefreshBase;
import com.zbmf.worklibrary.pulltorefresh.PullToRefreshListView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by xuhao on 2017/11/28.
 */

public class MatchListFragment extends BaseFragment<MatchFaragmentPresenter> implements IMatchFragment {

    @BindView(R.id.plv_match_list)
    PullToRefreshListView plvMatchList;
    private int flag;
    private HomeMatchAdapter adapter;


    public static MatchListFragment newInstance(int flag) {
        MatchListFragment fragment = new MatchListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(IntentKey.FLAG, flag);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_matchlist_layout;
    }

    @Override
    protected MatchFaragmentPresenter initPresent() {
        if (getArguments() != null) {
            flag = getArguments().getInt(IntentKey.FLAG);
        }
        return new MatchFaragmentPresenter(flag);
    }

    @Override
    protected void initView() {
        initProgressDialog(getString(R.string.loading_data));
        plvMatchList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                presenter.Refesh();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                presenter.LoadMore();
            }
        });
        plvMatchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ShowActivity.showMatchDetail(getActivity(),adapter.getItem(i-1));
            }
        });
    }
    @Override
    protected void initData() {
        adapter = new HomeMatchAdapter(getActivity());
        plvMatchList.setAdapter(adapter);
    }

    @Override
    public void RushMatchList(List<MatchBean> matchBeans) {
        adapter.setList(matchBeans);
        plvMatchList.onRefreshComplete();
    }
}
