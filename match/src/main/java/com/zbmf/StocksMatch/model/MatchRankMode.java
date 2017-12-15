package com.zbmf.StocksMatch.model;

import com.zbmf.StocksMatch.api.Method;
import com.zbmf.StocksMatch.api.SendParam;
import com.zbmf.StocksMatch.bean.BaseBean;
import com.zbmf.StocksMatch.bean.Yield;
import com.zbmf.StocksMatch.bean.YieldList;
import com.zbmf.StocksMatch.model.imode.IMatchRankMode;
import com.zbmf.worklibrary.model.CallBack;
import com.zbmf.worklibrary.pullrefreshrecycle.RefreshStatus;
import com.zbmf.worklibrary.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuhao on 2017/12/1.
 */

public class MatchRankMode extends BaseMatchMode implements IMatchRankMode{
    private List<Yield>yieldLists;
    @Override
    public void getMatchRankList(final RefreshStatus status, String matchid, String order, final int page, final CallBack callBack) {
        if(yieldLists==null){
            yieldLists=new ArrayList<>();
        }
        postSubscrube(Method.GET_MATCH_YIELD_LIST, SendParam.getYieldList(matchid, order, page), new CallBack() {
            @Override
            public void onSuccess(Object o) {
                BaseBean baseBean= GsonUtil.parseData(o,BaseBean.class);
                if(baseBean.getStatus()){
                    YieldList yieldList=GsonUtil.parseData(baseBean.getResult(),YieldList.class);
                    switch (status){
                        case LOAD_DEFAULT:
                            yieldLists.clear();
                            yieldLists.addAll(yieldList.getYields());
                            break;
                        case PULL_TO_REFRESH:
                            yieldLists.clear();
                            yieldLists.addAll(yieldList.getYields());
                            break;
                        case LOAD_MORE:
                            yieldLists.addAll(yieldList.getYields());
                            break;
                    }
                    yieldList.setYields(yieldLists);
                    callBack.onSuccess(yieldList);
                }else{
                    callBack.onFail(baseBean.getErr().getMsg());
                }
            }

            @Override
            public void onFail(String msg) {
                callBack.onFail(msg);
            }
        });
    }
}
