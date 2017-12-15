package com.zbmf.StocksMatch.bean;

import java.util.List;

/**
 * Created by xuhao on 2017/12/11.
 */

public class StockHoldList extends BaseListBean {
    private List<StockholdsBean>stocks;

    public List<StockholdsBean> getStocks() {
        return stocks;
    }

    public void setStocks(List<StockholdsBean> stocks) {
        this.stocks = stocks;
    }
}
