package com.zbmf.StocksMatch.listener;

import com.zbmf.StocksMatch.bean.MatchBean;
import com.zbmf.worklibrary.baseview.BaseView;

import java.util.List;

/**
 * Created by xuhao on 2017/11/28.
 */

public interface IMatchFragment extends BaseView {
    void RushMatchList(List<MatchBean>matchBeans);
}
