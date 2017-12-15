package com.zbmf.StocksMatch.presenter;

import com.zbmf.StocksMatch.bean.YieldList;
import com.zbmf.StocksMatch.listener.IMatchRankView;
import com.zbmf.StocksMatch.model.MatchRankMode;
import com.zbmf.worklibrary.model.CallBack;
import com.zbmf.worklibrary.presenter.BasePresenter;
import com.zbmf.worklibrary.pullrefreshrecycle.RefreshStatus;

/**
 * Created by xuhao on 2017/12/1.
 */

public class MatchRankPresenter extends BasePresenter <MatchRankMode,IMatchRankView>{
    private String match_id,order;
    private int page;
    public MatchRankPresenter(String match_id, String order){
        this.match_id=match_id;
        this.order=order;
    }
    @Override
    public void getDatas() {
        if(isFirst()){
            setFirst(false);
            getView().showLoading();
        }
        page=1;
        getMatchRank(RefreshStatus.LOAD_DEFAULT);
    }
    public void loadMore(){
        page+=1;
        getMatchRank(RefreshStatus.LOAD_MORE);
    }
    @Override
    public MatchRankMode initMode() {
        return new MatchRankMode();
    }
    private void getMatchRank(RefreshStatus status){
        getMode().getMatchRankList(status,match_id,order,page, new CallBack<YieldList>() {
            @Override
            public void onSuccess(YieldList yieldList) {
                page=yieldList.getPage();
                getView().RushDealList(yieldList.getYields());
                getView().dissLoading();
            }

            @Override
            public void onFail(String msg) {
                getView().showToastMsg(msg);
                getView().dissLoading();
            }
        });
    }
}
