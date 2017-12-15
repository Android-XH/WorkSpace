package com.zbmf.StocksMatch.model;

import com.zbmf.StocksMatch.api.HostUrl;

import com.zbmf.StocksMatch.constatns.SharedKey;
import com.zbmf.worklibrary.model.BaseMode;
import com.zbmf.worklibrary.util.SharedpreferencesUtil;

/**
 * Created by xuhao on 2017/11/22.
 */

public class BaseMatchMode extends BaseMode{
    @Override
    protected String getHost() {
        return SharedpreferencesUtil.getInstance().getString(SharedKey.MATCH_HOST,HostUrl.MATCH_URL);
    }
}
