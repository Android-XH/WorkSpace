package com.zbmf.StocksMatch.presenter;

import com.zbmf.StocksMatch.bean.DealSys;
import com.zbmf.StocksMatch.bean.DealsList;
import com.zbmf.StocksMatch.listener.IMatchNeaDealView;
import com.zbmf.StocksMatch.model.MatchNewDiealMode;
import com.zbmf.worklibrary.model.CallBack;
import com.zbmf.worklibrary.presenter.BasePresenter;
import com.zbmf.worklibrary.pullrefreshrecycle.RefreshStatus;

import java.util.List;

/**
 * Created by xuhao on 2017/12/1.
 */

public class MatchNewDealPresenter extends BasePresenter <MatchNewDiealMode,IMatchNeaDealView>{
    private int page;
    @Override
    public void getDatas() {
        if(isFirst()){
            setFirst(false);
            getView().showLoading();
        }
        page=1;
        getDealSys(RefreshStatus.LOAD_DEFAULT);
    }

    @Override
    public MatchNewDiealMode initMode() {
        return new MatchNewDiealMode();
    }
    public void loadMore(){
        page+=1;
        getDealSys(RefreshStatus.LOAD_MORE);
    }
    private void getDealSys(RefreshStatus status){

        getMode().getDealSys(page,status, new CallBack<DealsList>() {
            @Override
            public void onSuccess(DealsList dealSysList) {

                getView().dissLoading();
                page=dealSysList.getPage();
                getView().RushDealList(dealSysList.getDeals_sys());
            }

            @Override
            public void onFail(String msg) {
                getView().dissLoading();
                getView().showToastMsg(msg);
            }
        });
    }
}
