package com.zbmf.StocksMatch.fragment;

import com.zbmf.StocksMatch.R;
import com.zbmf.worklibrary.presenter.BasePresenter;

/**
 * Created by xuhao on 2017/11/27.
 */

public class StockFragment extends BaseFragment {
    public static StockFragment newInstance(){
        StockFragment fragment=new StockFragment();
        return fragment;
    }
    @Override
    protected int getLayout() {
        return R.layout.fragment_stock_layout;
    }

    @Override
    protected BasePresenter initPresent() {
        return null;
    }

    @Override
    protected void initView() {
        setTitleMessage(getString(R.string.stock));
    }

    @Override
    protected void initData() {

    }
}
