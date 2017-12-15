package com.zbmf.StocksMatch.listener;

import com.zbmf.StocksMatch.bean.MatchBean;
import com.zbmf.StocksMatch.bean.User;
import com.zbmf.worklibrary.baseview.BaseView;

import java.util.List;

/**
 * Created by xuhao on 2017/11/30.
 */

public interface IMineView extends BaseView{
    void RushMatchList(List<MatchBean>matchBeans);
    void RushMineMessage(User user);
    void onError();
}
