package com.zbmf.StocksMatch.presenter;

import com.zbmf.StocksMatch.bean.MatchList;
import com.zbmf.StocksMatch.listener.IMatchFragment;
import com.zbmf.StocksMatch.model.MatchListMode;
import com.zbmf.worklibrary.model.CallBack;
import com.zbmf.worklibrary.presenter.BasePresenter;
import com.zbmf.worklibrary.pullrefreshrecycle.RefreshStatus;


/**
 * Created by xuhao on 2017/11/28.
 */

public class MatchFaragmentPresenter extends BasePresenter<MatchListMode,IMatchFragment> {
    private int flag,page;
    public MatchFaragmentPresenter(int flag){
        this.flag=flag;
    }
    @Override
    public void getDatas() {
        if(isFirst()){
            setFirst(false);
            getView().showLoading();
        }
        page=1;
        getMatchList(RefreshStatus.LOAD_DEFAULT);
    }

    @Override
    public MatchListMode initMode() {
        return new MatchListMode();
    }
    public void Refesh(){
        page=1;
        getMatchList(RefreshStatus.PULL_TO_REFRESH);
    }
    public void LoadMore(){
        page+=1;
        getMatchList(RefreshStatus.LOAD_MORE);
    }
    private void getMatchList(RefreshStatus status){
        getMode().getMatchLsit(flag,page,status,new CallBack<MatchList>() {
            @Override
            public void onSuccess(MatchList matchList) {
                page=matchList.getPage();
                getView().RushMatchList(matchList.getMatch());
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
