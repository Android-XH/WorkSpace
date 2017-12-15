package com.zbmf.StocksMatch.model;

import com.zbmf.StocksMatch.api.Method;
import com.zbmf.StocksMatch.api.SendParam;
import com.zbmf.StocksMatch.bean.BaseBean;
import com.zbmf.StocksMatch.bean.DealSys;
import com.zbmf.StocksMatch.bean.DealsList;
import com.zbmf.StocksMatch.model.imode.IMatchNewMode;
import com.zbmf.worklibrary.model.CallBack;
import com.zbmf.worklibrary.pullrefreshrecycle.RefreshStatus;
import com.zbmf.worklibrary.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuhao on 2017/12/1.
 */

public class MatchNewDiealMode extends BaseMatchMode implements IMatchNewMode {
    private List<DealSys>infolist;
    @Override
    public void getDealSys(int page, final RefreshStatus status, final CallBack callBack) {
        if(infolist==null){
            infolist=new ArrayList<>();
        }
        postSubscrube(Method.DEALSYS, SendParam.dealSys(page), new CallBack() {
            @Override
            public void onSuccess(Object o) {
                BaseBean baseBean=GsonUtil.parseData(o,BaseBean.class);
                if(baseBean.getStatus()){
                    DealsList dealsList= GsonUtil.parseData(baseBean.getResult(),DealsList.class);
                    switch (status){
                        case LOAD_DEFAULT:
                            infolist.clear();
                            infolist.addAll(dealsList.getDeals_sys());
                            break;
                        case PULL_TO_REFRESH:
                            infolist.clear();
                            infolist.addAll(dealsList.getDeals_sys());
                            break;
                        case LOAD_MORE:
                            infolist.addAll(dealsList.getDeals_sys());
                            break;
                    }
                    dealsList.setDeals_sys(infolist);
                    callBack.onSuccess(dealsList);
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
