package com.zbmf.StocksMatch.presenter;

import com.zbmf.StocksMatch.bean.MatchInfo;
import com.zbmf.StocksMatch.bean.StockholdsBean;
import com.zbmf.StocksMatch.listener.IMatchDetailView;
import com.zbmf.StocksMatch.model.MatchDeatilMode;
import com.zbmf.worklibrary.model.CallBack;
import com.zbmf.worklibrary.presenter.BasePresenter;

import java.util.List;

/**
 * Created by xuhao on 2017/11/29.
 */

public class MatchDetailPresenter extends BasePresenter<MatchDeatilMode,IMatchDetailView> {
    private String matchid;
    public MatchDetailPresenter(String matchid){
        this.matchid=matchid;
    }
    @Override
    public void getDatas() {
        if(isFirst()){
            setFirst(false);
            getView().showLoading();
        }
        getMatchDetail();
        getMode().getMatchHold(matchid, new CallBack<List<StockholdsBean>>() {
            @Override
            public void onSuccess(List<StockholdsBean> stockholdsBeans) {
                getView().RushMatchHold(stockholdsBeans);
                setFirst(false);
            }

            @Override
            public void onFail(String msg) {
                getView().showToastMsg(msg);
                setFirst(false);
            }
        });
    }

    @Override
    public MatchDeatilMode initMode() {
        return new MatchDeatilMode();
    }
    private void getMatchDetail(){
        getMode().getMatchDetail(matchid, new CallBack<MatchInfo>() {
            @Override
            public void onSuccess(MatchInfo matchInfo) {
                getView().dissLoading();
                getView().RushMatchDetail(matchInfo);
            }

            @Override
            public void onFail(String msg) {
                getView().dissLoading();
                getView().showToastMsg(msg);
            }
        });
    }
}
