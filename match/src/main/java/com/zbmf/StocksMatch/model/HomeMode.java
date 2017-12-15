package com.zbmf.StocksMatch.model;

import com.zbmf.StocksMatch.api.Method;
import com.zbmf.StocksMatch.api.SendParam;
import com.zbmf.StocksMatch.bean.City;
import com.zbmf.StocksMatch.bean.MatchBean;
import com.zbmf.StocksMatch.bean.MatchList;

import java.util.ArrayList;
import java.util.List;

import com.zbmf.StocksMatch.bean.MatchSchool;
import com.zbmf.StocksMatch.bean.PhoneAd;
import com.zbmf.StocksMatch.bean.PhoneAdList;
import com.zbmf.StocksMatch.bean.School;
import com.zbmf.StocksMatch.bean.TraderList;
import com.zbmf.StocksMatch.model.imode.IHomeMode;
import com.zbmf.worklibrary.model.CallBack;
import com.zbmf.worklibrary.util.GsonUtil;
import com.zbmf.worklibrary.pullrefreshrecycle.RefreshStatus;

/**
 * Created by xuhao on 2017/11/22.
 */

public class HomeMode extends BaseMatchMode implements IHomeMode {
    @Override
    public void getSupremeMatch(final CallBack callBack) {
        postSubscrube(Method.SUPREME_MATCH, SendParam.getSupremeMatch(), new CallBack() {
            @Override
            public void onSuccess(Object o) {
                MatchList matchList=GsonUtil.parseData(o,MatchList.class);
                if(matchList.getStatus()){
                    callBack.onSuccess(matchList.getMatch());
                }else{
                    callBack.onFail(matchList.getErr().getMsg());
                }

            }

            @Override
            public void onFail(String msg) {
                callBack.onFail(msg);
            }
        });
    }

    @Override
    public void getImageList(final CallBack callBack) {
        postSubscrube(Method.RECOMMEND, SendParam.getReconpic(), new CallBack() {
            @Override
            public void onSuccess(Object o) {
                PhoneAdList phoneAdList=GsonUtil.parseData(o,PhoneAdList.class);
                if(phoneAdList.getStatus()){
                    callBack.onSuccess(phoneAdList.getPhone_ads());
                }else{
                    callBack.onFail(phoneAdList.getErr().getMsg());
                }
            }

            @Override
            public void onFail(String msg) {
                callBack.onFail(msg);
            }
        });

    }

    @Override
    public void getMatchSchool(CallBack callBack) {
        MatchSchool matchSchool=new MatchSchool();
        matchSchool.setMatch_num(100);
        matchSchool.setMatch_player(1000);
        List<School>schools=new ArrayList<>();
        for(int i=0;i<10;i++){
            School school=new School();
            school.setName("清华大学");
            school.setLogo("http://www.hhxx.com.cn/uploads/allimg/1609/276-1609120PQ3K1.jpg");
            schools.add(school);
        }
        matchSchool.setSchools(schools);
        callBack.onSuccess(matchSchool);
    }

    @Override
    public void getTrader(final CallBack callBack) {
        postSubscrube(Method.TRADER_RANKS, SendParam.getTraders(), new CallBack() {
            @Override
            public void onSuccess(Object o) {
                TraderList traderList=GsonUtil.parseData(o,TraderList.class);
                if(traderList.getStatus()){
                    if(traderList.getTraders().size()>3){
                        callBack.onSuccess(traderList.getTraders().subList(0,3));
                    }else{
                        callBack.onSuccess(traderList.getTraders());
                    }
                }else{
                    callBack.onFail(traderList.getErr().getMsg());
                }
            }

            @Override
            public void onFail(String msg) {
                callBack.onFail(msg);
            }
        });
    }

    @Override
    public void getCity(CallBack callBack) {
        List<City>cities=new ArrayList<>();
        for(int i=0;i<6;i++){
            cities.add(new City("北京"));
        }
        callBack.onSuccess(cities);
    }

    @Override
    public void getHotMatch(final CallBack callBack) {
        postSubscrube(Method.SUPREME_MATCH, SendParam.getSupremeMatch(), new CallBack() {
            @Override
            public void onSuccess(Object o) {
                MatchList matchList=GsonUtil.parseData(o,MatchList.class);
                if(matchList.getStatus()){
                    callBack.onSuccess(matchList.getMatch());
                }else{
                    callBack.onFail(matchList.getErr().getMsg());
                }
            }

            @Override
            public void onFail(String msg) {
                callBack.onFail(msg);
            }
        });
    }
}
