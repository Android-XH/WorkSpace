package com.zbmf.StocksMatch.listener;

import com.zbmf.StocksMatch.bean.MatchInfo;
import com.zbmf.StocksMatch.bean.StockholdsBean;
import com.zbmf.worklibrary.baseview.BaseView;

import java.util.List;

/**
 * Created by xuhao on 2017/11/29.
 */

public interface IMatchDetailView extends BaseView{
    void RushMatchDetail(MatchInfo matchInfo);
    void RushMatchHold(List<StockholdsBean> stockholdsBeanList);
}
