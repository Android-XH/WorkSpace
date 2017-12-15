package com.zbmf.StocksMatch.bean;

import java.util.List;

/**
 * Created by xuhao on 2017/11/30.
 */

public class PhoneAdList extends BaseBean {
    private int match_count;
    private List<PhoneAd>phone_ads;

    public int getMatch_count() {
        return match_count;
    }

    public void setMatch_count(int match_count) {
        this.match_count = match_count;
    }

    public List<PhoneAd> getPhone_ads() {
        return phone_ads;
    }

    public void setPhone_ads(List<PhoneAd> phone_ads) {
        this.phone_ads = phone_ads;
    }
}
