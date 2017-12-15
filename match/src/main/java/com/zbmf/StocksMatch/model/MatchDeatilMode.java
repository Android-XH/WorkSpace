package com.zbmf.StocksMatch.model;

import com.zbmf.StocksMatch.api.Method;
import com.zbmf.StocksMatch.api.SendParam;
import com.zbmf.StocksMatch.bean.BaseBean;
import com.zbmf.StocksMatch.bean.MatchInfo;
import com.zbmf.StocksMatch.bean.StockHoldList;
import com.zbmf.StocksMatch.model.imode.IMatchDetailMode;
import com.zbmf.worklibrary.model.CallBack;
import com.zbmf.worklibrary.util.GsonUtil;

/**
 * Created by xuhao on 2017/11/29.
 */

public class MatchDeatilMode extends BaseMatchMode implements IMatchDetailMode{

    @Override
    public void getMatchDetail(final String matchId, final CallBack callBack) {
        postSubscrube(Method.GETPLAYER, SendParam.getMatchDetail(matchId), new CallBack() {
            @Override
            public void onSuccess(Object o) {
                MatchInfo matchInfo= GsonUtil.parseData(o,MatchInfo.class);
                if(matchInfo.getStatus()){
                    callBack.onSuccess(matchInfo);
                }else{
                    callBack.onFail(matchInfo.getErr().getMsg());
                }
            }

            @Override
            public void onFail(String msg) {
                callBack.onFail(msg);
            }
        });
    }

    @Override
    public void getMatchHold(String matchId, final CallBack callBack) {
        postSubscrube(Method.GET_HOLD_LIST, SendParam.getHoldList(matchId), new CallBack() {
            @Override
            public void onSuccess(Object o) {
                BaseBean baseBean=GsonUtil.parseData(o,BaseBean.class);
                if(baseBean.getStatus()&&baseBean.getResult()!=null){
                    StockHoldList stockHoldList=GsonUtil.parseData(baseBean.getResult(),StockHoldList.class);
                    callBack.onSuccess(stockHoldList.getStocks());
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
