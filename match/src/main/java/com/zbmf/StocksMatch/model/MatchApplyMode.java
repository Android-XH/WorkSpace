package com.zbmf.StocksMatch.model;

import com.zbmf.StocksMatch.api.Method;
import com.zbmf.StocksMatch.api.SendParam;
import com.zbmf.StocksMatch.model.imode.IMatchApplyMode;
import com.zbmf.worklibrary.model.CallBack;

import java.util.Map;

/**
 * Created by xuhao on 2017/12/12.
 */

public class MatchApplyMode extends BaseMatchMode implements IMatchApplyMode {

    @Override
    public void onApplyMatch(Map<String, String> map, final CallBack callBack) {
        postSubscrube(Method.VERIFY_CODE, SendParam.getApplyMatch(map), new CallBack() {
            @Override
            public void onSuccess(Object o) {
                callBack.onSuccess(o);
            }

            @Override
            public void onFail(String msg) {
                callBack.onFail(msg);
            }
        });
    }
}
