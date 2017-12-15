package com.zbmf.StocksMatch.listener;

import com.zbmf.StocksMatch.bean.MatchInfo;
import com.zbmf.StocksMatch.bean.StockholdsBean;
import com.zbmf.worklibrary.baseview.BaseView;

import java.util.List;

/**
 * Created by xuhao on 2017/12/11.
 */

public interface IDrillFragment extends BaseView{
    void rushMatchBean(MatchInfo matchBean);
    void rushHold(List<StockholdsBean>stockholdsBeans);
}
