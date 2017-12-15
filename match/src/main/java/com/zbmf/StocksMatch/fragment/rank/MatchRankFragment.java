package com.zbmf.StocksMatch.fragment.rank;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zbmf.StocksMatch.R;
import com.zbmf.StocksMatch.adapter.MatchRankAdapter;
import com.zbmf.StocksMatch.bean.Yield;
import com.zbmf.StocksMatch.constatns.Constans;
import com.zbmf.StocksMatch.constatns.IntentKey;
import com.zbmf.StocksMatch.fragment.BaseFragment;
import com.zbmf.StocksMatch.listener.IMatchRankView;
import com.zbmf.StocksMatch.presenter.MatchRankPresenter;
import com.zbmf.worklibrary.presenter.BasePresenter;
import com.zbmf.worklibrary.pulltorefresh.PullToRefreshBase;
import com.zbmf.worklibrary.pulltorefresh.PullToRefreshListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xuhao on 2017/12/1.
 */

public class MatchRankFragment extends BaseFragment<MatchRankPresenter> implements IMatchRankView{
    @BindView(R.id.new_rank_listview)
    PullToRefreshListView newRankListview;
    private String order,match_id;
    private MatchRankAdapter adapter;
    private int flag;
    public static MatchRankFragment newInstance(String match_id,int flag) {
        MatchRankFragment matchRankFragment = new MatchRankFragment();
        Bundle bundle = new Bundle();
        bundle.putString(IntentKey.MATCH_ID,match_id);
        bundle.putInt(IntentKey.FLAG, flag);
        matchRankFragment.setArguments(bundle);
        return matchRankFragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_match_rank_layout;
    }

    @Override
    protected void initView() {
        initProgressDialog(getString(R.string.loading_data));
        newRankListview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                presenter.getDatas();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                presenter.loadMore();
            }
        });
        newRankListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    @Override
    protected void initData() {
        adapter=new MatchRankAdapter(getActivity(),flag);
        newRankListview.setAdapter(adapter);
    }

    @Override
    protected MatchRankPresenter initPresent() {
        Bundle bundle=getArguments();
        if(bundle!=null){
            flag = getArguments().getInt(IntentKey.FLAG);
            switch (flag){
                case Constans.DAY_RANK:
                    order="1";
                    break;
                case Constans.WEEK_RANK:
                    order="7";
                    break;
                case Constans.MOUNTH_RANK:
                    order="30";
                    break;
                case Constans.ALL_RANK:
                    order="0";
                    break;
            }
            match_id=bundle.getString(IntentKey.MATCH_ID);
        }
        return new MatchRankPresenter(match_id,order);
    }

    @Override
    public void RushDealList(List<Yield> yieldList) {
        adapter.setList(yieldList);
        if(newRankListview.isRefreshing()){
            newRankListview.onRefreshComplete();
        }
    }
}
