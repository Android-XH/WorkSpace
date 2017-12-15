package com.zbmf.StocksMatch.listener;

import com.zbmf.StocksMatch.bean.DealSys;
import com.zbmf.worklibrary.baseview.BaseView;

import java.util.List;

/**
 * Created by xuhao on 2017/12/1.
 */

public interface IMatchNeaDealView extends BaseView {
    void RushDealList(List<DealSys>dealSysList);
}
