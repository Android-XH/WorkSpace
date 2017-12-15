package com.zbmf.StocksMatch.listener;

import com.zbmf.StocksMatch.bean.City;
import com.zbmf.StocksMatch.bean.MatchBean;

import java.util.List;

import com.zbmf.StocksMatch.bean.MatchSchool;
import com.zbmf.StocksMatch.bean.PhoneAd;
import com.zbmf.StocksMatch.bean.Traders;
import com.zbmf.worklibrary.baseview.BaseView;

/**
 * Created by xuhao on 2017/11/22.
 */

public interface IHomeView extends BaseView{
    void RusnSupremeMatchAdapter(List<MatchBean>matchBeans);
    void RushBannerImage(List<PhoneAd>imgList);
    void RushMatchSchool(MatchSchool matchSchool);
    void RushTraderList(List<Traders>traders);
    void RushHostMatch(List<MatchBean>matchBeans);
    void RushCity(List<City>cityList);
    void RushScrollView();
}
