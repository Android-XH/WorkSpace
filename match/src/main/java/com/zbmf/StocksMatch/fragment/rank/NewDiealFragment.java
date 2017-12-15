package com.zbmf.StocksMatch.fragment.rank;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zbmf.StocksMatch.R;
import com.zbmf.StocksMatch.adapter.MatchNewDiealAdapter;
import com.zbmf.StocksMatch.adapterinterface.OnAdapterClickListener;
import com.zbmf.StocksMatch.bean.DealSys;
import com.zbmf.StocksMatch.fragment.BaseFragment;
import com.zbmf.StocksMatch.listener.IMatchNeaDealView;
import com.zbmf.StocksMatch.presenter.MatchNewDealPresenter;
import com.zbmf.worklibrary.pulltorefresh.PullToRefreshBase;
import com.zbmf.worklibrary.pulltorefresh.PullToRefreshListView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by xuhao on 2017/12/1.
 */

public class NewDiealFragment extends BaseFragment<MatchNewDealPresenter> implements OnAdapterClickListener,IMatchNeaDealView{

    @BindView(R.id.new_dieal_listview)
    PullToRefreshListView newDiealListview;

    private MatchNewDiealAdapter adapter;
    public static NewDiealFragment newInstance() {
        NewDiealFragment newDiealFragment = new NewDiealFragment();
        return newDiealFragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_new_dieal_layout;
    }

    @Override
    protected void initView() {
        initProgressDialog(getString(R.string.loading_data));
        newDiealListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        newDiealListview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
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
        adapter=new MatchNewDiealAdapter(getActivity());
        adapter.setOnAdapterClickListener(this);
        newDiealListview.setAdapter(adapter);
    }

    @Override
    protected MatchNewDealPresenter initPresent() {
        return new MatchNewDealPresenter();
    }

    @Override
    public void onClickBuyListener(DealSys dealSys) {

    }

    @Override
    public void onClickClBuyListener(DealSys dealSys) {

    }

    @Override
    public void onClickCommentListener(DealSys dealSys) {

    }

    @Override
    public void RushDealList(List<DealSys> dealSysList) {
        adapter.setList(dealSysList);
        if(newDiealListview.isRefreshing()){
            newDiealListview.onRefreshComplete();
        }
    }
}
