package com.zbmf.StocksMatch.presenter;

import com.zbmf.StocksMatch.bean.City;
import com.zbmf.StocksMatch.bean.MatchBean;
import com.zbmf.StocksMatch.bean.MatchSchool;
import com.zbmf.StocksMatch.bean.PhoneAd;
import com.zbmf.StocksMatch.bean.Traders;
import com.zbmf.StocksMatch.listener.IHomeView;
import com.zbmf.StocksMatch.model.HomeMode;
import com.zbmf.worklibrary.model.CallBack;
import com.zbmf.worklibrary.presenter.BasePresenter;
import com.zbmf.worklibrary.pullrefreshrecycle.RefreshStatus;

import java.util.List;

/**
 * Created by xuhao on 2017/11/24.
 */

public class HomePresenter extends BasePresenter<HomeMode,IHomeView>{
    private boolean supreMatch,bannerImg,matchSchool;
    @Override
    public void getDatas() {
        if(isFirst()){
            setFirst(false);
            getView().showLoading();
        }
        getSupremeMatch();
        getImageList();
        getMatchSchool();
        getMatchTrader();
        getHotMatch();
        getCityList();
    }

    @Override
    public HomeMode initMode() {
        return new HomeMode();
    }
    public void getSupremeMatch(){
        getMode().getSupremeMatch(new CallBack<List<MatchBean>>() {
            @Override
            public void onSuccess(List<MatchBean> matchBeans) {
                getView().RusnSupremeMatchAdapter(matchBeans);
                supreMatch=true;
                Rush();
            }

            @Override
            public void onFail(String msg) {
                supreMatch=true;
                Rush();
            }
        });
    }
    public void getMatchTrader(){
        getMode().getTrader(new CallBack<List<Traders>>() {
            @Override
            public void onSuccess(List<Traders> tradersList) {
                getView().RushTraderList(tradersList);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
    public void getImageList(){
        getMode().getImageList(new CallBack<List<PhoneAd>>() {
            @Override
            public void onSuccess(List<PhoneAd> imagelist) {
                getView().RushBannerImage(imagelist);
                bannerImg=true;
                Rush();
            }

            @Override
            public void onFail(String msg) {
                bannerImg=true;
                Rush();
            }
        });
    }
    public void getMatchSchool(){
        getMode().getMatchSchool(new CallBack<MatchSchool>() {
            @Override
            public void onSuccess(MatchSchool match) {
                getView().RushMatchSchool(match);
                matchSchool=true;
                Rush();
            }

            @Override
            public void onFail(String msg) {
                matchSchool=true;
                Rush();
            }
        });
    }
    private void getHotMatch(){
        getMode().getHotMatch(new CallBack<List<MatchBean>>() {
            @Override
            public void onSuccess(List<MatchBean> matchBeans) {
                getView().RushHostMatch(matchBeans);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
    private void getCityList(){
        getMode().getCity(new CallBack<List<City>>() {
            @Override
            public void onSuccess(List<City> cities) {
                getView().RushCity(cities);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    private void Rush(){
        if(bannerImg&&supreMatch&&matchSchool){
            getView().dissLoading();
            getView().RushScrollView();
        }
    }
}
