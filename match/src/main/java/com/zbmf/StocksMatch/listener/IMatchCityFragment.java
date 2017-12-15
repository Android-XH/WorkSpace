package com.zbmf.StocksMatch.listener;

import com.zbmf.StocksMatch.bean.City;
import com.zbmf.StocksMatch.bean.MatchBean;
import com.zbmf.worklibrary.baseview.BaseView;

import java.util.List;

/**
 * Created by xuhao on 2017/11/28.
 */

public interface IMatchCityFragment extends BaseView {
    void RushCityList(List<City> matchBeans);
}
