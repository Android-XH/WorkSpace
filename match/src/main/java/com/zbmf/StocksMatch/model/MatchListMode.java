package com.zbmf.StocksMatch.model;


import com.zbmf.StocksMatch.api.Method;
import com.zbmf.StocksMatch.api.SendParam;
import com.zbmf.StocksMatch.bean.BaseBean;
import com.zbmf.StocksMatch.bean.MatchBean;
import com.zbmf.StocksMatch.bean.MatchList;
import com.zbmf.StocksMatch.constatns.Constans;
import com.zbmf.StocksMatch.model.imode.IMatchFragmentMode;
import com.zbmf.worklibrary.model.CallBack;
import com.zbmf.worklibrary.pullrefreshrecycle.RefreshStatus;
import com.zbmf.worklibrary.util.GsonUtil;
import com.zbmf.worklibrary.util.Logx;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取比赛列表
 * All_MATCH 所有比赛
 * NEW_MATCH 最新比赛
 * Created by xuhao on 2017/11/28.
 */

public class MatchListMode extends BaseMatchMode implements IMatchFragmentMode {
    private List<MatchBean>matchBeans;
    @Override
    public void getMatchLsit(int flag, int page, final RefreshStatus status, final CallBack callBack) {
        if(matchBeans==null){
            matchBeans=new ArrayList<>();
        }
        String method="";
        switch (flag) {
            case Constans.All_MATCH:
                method=Method.GET_MATCH_NOSTOPLIST;
                break;
            case Constans.NEW_MATCH:
                method=Method.GET_MATCH_NOSTOPLIST;
                break;
        }
        postSubscrube(method, SendParam.getMatchList(page), new CallBack() {
            @Override
            public void onSuccess(Object o) {
                BaseBean baseBean = GsonUtil.parseData(o,BaseBean.class);
                Logx.e(baseBean.toString());
                if(baseBean.getStatus()&&baseBean.getResult()!=null){
                    MatchList matchList1=GsonUtil.parseData(baseBean.getResult(),MatchList.class);
                    switch (status){
                        case LOAD_DEFAULT:
                            matchBeans.clear();
                            matchBeans.addAll(matchList1.getMatches());
                            break;
                        case LOAD_MORE:
                            matchBeans.addAll(matchList1.getMatches());
                            break;
                        case PULL_TO_REFRESH:
                            matchBeans.clear();
                            matchBeans.addAll(matchList1.getMatches());
                            break;
                    }
                    matchList1.setMatch(matchBeans);
                    callBack.onSuccess(matchList1);
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
