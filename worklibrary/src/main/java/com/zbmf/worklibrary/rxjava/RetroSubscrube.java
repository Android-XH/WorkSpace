package com.zbmf.worklibrary.rxjava;


import com.zbmf.worklibrary.model.CallBack;

import java.util.Map;
import io.reactivex.Observable;

/**
 * Created by xuhao on 2017/6/13.
 */

public class RetroSubscrube {
    public static RetroSubscrube retroSubscrube;

    public static RetroSubscrube getInstance() {
        if (retroSubscrube == null) {
            retroSubscrube = new RetroSubscrube();
        }
        return retroSubscrube;
    }

    /**
     * 从服务器获取数据
     *
     * @param map
     * @param callBack
     */
    public void getSubscrube(String url,Map<String, String> map,CallBack callBack) {
        BaseObserver observer=new BaseObserver(callBack);
        Observable<Object> observable = RetroFactory.getInstance(url).get(url,map);
        observable.compose(RxSchedulers.compose()).subscribe(observer);
    }

    /**
     * 提交数据到服务器
     *
     * @param map
     * @param callBack
     */
    public void postSubscrube(String url,String op, Map<String, String> map, CallBack callBack) {
        BaseObserver observer=new BaseObserver(callBack);
        Observable<Object> observable = RetroFactory.getInstance(url).post(url,op, map);
        observable.compose(RxSchedulers.compose()).subscribe(observer);
    }

}
