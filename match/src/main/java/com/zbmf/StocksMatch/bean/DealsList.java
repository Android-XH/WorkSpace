package com.zbmf.StocksMatch.bean;

import java.util.List;

/**
 * Created by xuhao on 2017/12/1.
 */

public class DealsList extends BaseListBean {
    private List<DealSys>deals_sys;
    public List<DealSys> getDeals_sys() {
        return deals_sys;
    }
    public void setDeals_sys(List<DealSys> deals_sys) {
        this.deals_sys = deals_sys;
    }
}
