package com.zbmf.StocksMatch.model.imode;

import com.zbmf.worklibrary.model.CallBack;
import com.zbmf.worklibrary.pullrefreshrecycle.RefreshStatus;

/**
 * Created by xuhao on 2017/12/1.
 */

public interface IMatchNewMode {
    void getDealSys(int page, RefreshStatus status, CallBack callBack);
}
