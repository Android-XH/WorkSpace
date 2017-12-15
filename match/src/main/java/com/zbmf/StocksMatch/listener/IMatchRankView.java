package com.zbmf.StocksMatch.listener;

import com.zbmf.StocksMatch.bean.Yield;
import com.zbmf.worklibrary.baseview.BaseView;

import java.util.List;

/**
 * Created by xuhao on 2017/12/1.
 */

public interface IMatchRankView extends BaseView {
    void RushDealList(List<Yield> yieldList);
}
