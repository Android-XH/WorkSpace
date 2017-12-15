package com.zbmf.StocksMatch.model.imode;

import com.zbmf.worklibrary.model.CallBack;

/**
 * Created by xuhao on 2017/11/29.
 */

public interface IMatchDetailMode {
    void getMatchDetail(String matchId, CallBack callBack);
    void getMatchHold(String matchId,CallBack callBack);
}
