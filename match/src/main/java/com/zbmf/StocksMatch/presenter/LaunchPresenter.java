package com.zbmf.StocksMatch.presenter;

import com.zbmf.StocksMatch.api.HostUrl;
import com.zbmf.StocksMatch.bean.Address;
import com.zbmf.StocksMatch.bean.Vers;
import com.zbmf.StocksMatch.constatns.SharedKey;
import com.zbmf.StocksMatch.listener.IlaunchView;
import com.zbmf.StocksMatch.model.LaunchMode;
import com.zbmf.worklibrary.model.CallBack;
import com.zbmf.worklibrary.presenter.BasePresenter;
import com.zbmf.worklibrary.util.SharedpreferencesUtil;

/**
 * Created by xuhao on 2017/11/24.
 */

public class LaunchPresenter extends BasePresenter<LaunchMode,IlaunchView> {
    @Override
    public void getDatas() {
        getMode().getVers(new CallBack<Vers>() {
            @Override
            public void onSuccess(Vers v) {
                Address address=v.getAddress();
                Address.group group=address.getGroup();
                HostUrl.GROUP_URL=group.getHost()+group.getApi();
                Address.match match=address.getMatch();
                HostUrl.MATCH_URL=match.getHost()+match.getApi();
                Address.www www=address.getWww();
                HostUrl.WWW_URL=www.getHost()+www.getApi();
                Address.passport passport=address.getPassport();
                HostUrl.PASS_URL=passport.getHost()+passport.getApi();
                SharedpreferencesUtil.getInstance().putString(SharedKey.GROUP_HOST,HostUrl.GROUP_URL);
                SharedpreferencesUtil.getInstance().putString(SharedKey.MATCH_HOST,HostUrl.MATCH_URL);
                SharedpreferencesUtil.getInstance().putString(SharedKey.WWW_HOST,HostUrl.WWW_URL);
                SharedpreferencesUtil.getInstance().putString(SharedKey.PASS_HOST,HostUrl.PASS_URL);
                getView().toLogin();
            }

            @Override
            public void onFail(String msg) {
                getView().showToastMsg(msg);
                getView().toLogin();
            }
        });
    }

    @Override
    public LaunchMode initMode() {
        return new LaunchMode();
    }
}
