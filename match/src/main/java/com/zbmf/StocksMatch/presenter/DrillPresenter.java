package com.zbmf.StocksMatch.presenter;

import com.zbmf.StocksMatch.bean.MatchInfo;
import com.zbmf.StocksMatch.bean.StockholdsBean;
import com.zbmf.StocksMatch.listener.IDrillFragment;
import com.zbmf.StocksMatch.model.MatchDeatilMode;
import com.zbmf.worklibrary.model.CallBack;
import com.zbmf.worklibrary.presenter.BasePresenter;

import java.util.List;

/**
 * Created by xuhao on 2017/12/11.
 */

public class DrillPresenter extends BasePresenter<MatchDeatilMode,IDrillFragment> {
    private String matchId;
    public DrillPresenter(String matchId){
        this.matchId=matchId;
    }
    @Override
    public void getDatas() {
        if(isFirst()){
            getView().showLoading();
        }
        getMode().getMatchDetail(matchId, new CallBack<MatchInfo>() {
            @Override
            public void onSuccess(MatchInfo matchBean) {
                getView().rushMatchBean(matchBean);
                setFirst(false);
            }

            @Override
            public void onFail(String msg) {
                getView().showToastMsg(msg);
                setFirst(false);
            }
        });
        getMode().getMatchHold(matchId, new CallBack<List<StockholdsBean>>() {
            @Override
            public void onSuccess(List<StockholdsBean> stockholdsBeans) {
                getView().rushHold(stockholdsBeans);
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
}
