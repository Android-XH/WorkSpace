package com.zbmf.StocksMatch.presenter;

import com.zbmf.StocksMatch.api.ParamsKey;
import com.zbmf.StocksMatch.listener.IApplyMatchView;
import com.zbmf.StocksMatch.model.MatchApplyMode;
import com.zbmf.worklibrary.model.CallBack;
import com.zbmf.worklibrary.presenter.BasePresenter;

import java.util.Map;

/**
 * Created by xuhao on 2017/12/12.
 */

public class ApplyMatchPresenter extends BasePresenter<MatchApplyMode, IApplyMatchView> {
    private String matchId;

    public ApplyMatchPresenter(String matchId) {
        this.matchId = matchId;
    }

    @Override
    public void getDatas() {

    }

    @Override
    public MatchApplyMode initMode() {
        return new MatchApplyMode();
    }

    public void AppleMatchMode(Map<String,String> map) {
        map.put(ParamsKey.MATCH_ID,matchId);
        getMode().onApplyMatch(map, new CallBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onSucceed();
            }

            @Override
            public void onFail(String msg) {
                getView().showToastMsg(msg);
            }
        });
    }
}
