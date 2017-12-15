package com.zbmf.StocksMatch.presenter;


import com.zbmf.StocksMatch.bean.City;
import com.zbmf.StocksMatch.listener.IMatchCityFragment;
import com.zbmf.StocksMatch.model.MatchCityListMode;
import com.zbmf.worklibrary.model.CallBack;
import com.zbmf.worklibrary.presenter.BasePresenter;
import com.zbmf.worklibrary.pullrefreshrecycle.RefreshStatus;

import java.util.List;

/**
 * 获取比赛城市列表
 * Created by xuhao on 2017/11/29.
 */

public class MatchCityPresenter extends BasePresenter<MatchCityListMode,IMatchCityFragment> {
    private int page;
    private int flag;
    public MatchCityPresenter(int flag){
        this.flag=flag;
    }
    @Override
    public void getDatas() {
        if(isFirst()){
            setFirst(false);
            getView().showLoading();
        }
        page=1;
        getCityList(RefreshStatus.LOAD_DEFAULT);
    }
    public void loadMore(){
        page+=1;
        getCityList(RefreshStatus.LOAD_MORE);
    }
    @Override
    public MatchCityListMode initMode() {
        return new MatchCityListMode();
    }
    private void getCityList(RefreshStatus status){
        getMode().getMatchLsit(flag, page, status, new CallBack<List<City>>() {
            @Override
            public void onSuccess(List<City>cities) {
                getView().RushCityList(cities);
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
