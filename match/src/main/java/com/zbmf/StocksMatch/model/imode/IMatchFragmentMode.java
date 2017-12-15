package com.zbmf.StocksMatch.model.imode;

import com.zbmf.worklibrary.model.CallBack;
import com.zbmf.worklibrary.pullrefreshrecycle.RefreshStatus;

/**
 * Created by xuhao on 2017/11/28.
 */

public interface IMatchFragmentMode {
    void getMatchLsit(int flag, int page, RefreshStatus status, CallBack callBack);
}
