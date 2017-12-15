package com.zbmf.StocksMatch.presenter;

import com.zbmf.StocksMatch.bean.MatchBean;
import com.zbmf.StocksMatch.bean.MatchList;
import com.zbmf.StocksMatch.bean.User;
import com.zbmf.StocksMatch.listener.IMineView;
import com.zbmf.StocksMatch.model.MineMode;
import com.zbmf.worklibrary.model.CallBack;
import com.zbmf.worklibrary.presenter.BasePresenter;
import com.zbmf.worklibrary.pullrefreshrecycle.RefreshStatus;

import java.util.List;

/**
 * Created by xuhao on 2017/11/30.
 */

public class MinePresenter extends BasePresenter<MineMode,IMineView> {
    private int page;
    @Override
    public void getDatas() {
        page=1;
        getUserInfo();
        getMatchList(RefreshStatus.LOAD_DEFAULT);
    }

    @Override
    public MineMode initMode() {
        return new MineMode();
    }

    private void getMatchList(RefreshStatus status){
        getMode().getMatchList(status,new CallBack<MatchList>() {
            @Override
            public void onSuccess(MatchList matchList) {
                page=matchList.getPage();
                getView().RushMatchList(matchList.getMatches());
            }

            @Override
            public void onFail(String msg) {
                getView().showToastMsg(msg);
                getView().onError();
            }
        });
    }
    private void getUserInfo(){
        getMode().getMineDetail(new CallBack<User>() {
            @Override
            public void onSuccess(User user) {
                getView().RushMineMessage(user);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
