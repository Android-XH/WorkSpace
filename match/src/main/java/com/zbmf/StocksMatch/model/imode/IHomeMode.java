package com.zbmf.StocksMatch.model.imode;

import com.zbmf.StocksMatch.bean.MatchBean;

import java.util.List;

import com.zbmf.worklibrary.model.CallBack;
import com.zbmf.worklibrary.pullrefreshrecycle.RefreshStatus;

/**
 * Created by xuhao on 2017/11/22.
 */

public interface IHomeMode {
    void getSupremeMatch(CallBack callBack);
    void getImageList(CallBack callBack);
    void getMatchSchool(CallBack callBack);
    void getTrader(CallBack callBack);
    void getCity(CallBack callBack);
    void getHotMatch(CallBack callBack);
}
