package com.zbmf.worklibrary.rxjava;




import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import com.zbmf.worklibrary.model.CallBack;
import com.zbmf.worklibrary.util.GsonUtil;
import com.zbmf.worklibrary.util.Logx;

/**
 * Created by xuhao on 2017/6/12.
 */

public class BaseObserver implements Observer<Object> {
    private Disposable mDisposable;
    private CallBack callBack;
    private boolean isDestory;
    public BaseObserver(CallBack callBack){
        this.callBack=callBack;
    }
    public BaseObserver(boolean isDestory,CallBack callBack){
        this.callBack=callBack;
        this.isDestory=isDestory;
    }
    @Override
    public void onSubscribe(Disposable d) {
        this.mDisposable = d;
    }

    @Override
    public void onNext(Object value) {
        Logx.json(GsonUtil.toJson(value));
        if(!isDestory){
            callBack.onSuccess(value);
        }
    }
    @Override
    public void onError(Throwable e) {
        if(!isDestory){
            callBack.onFail(e.getMessage());
        }

    }

    @Override
    public void onComplete() {
        Logx.e("获取数据完成,销毁线程");
        if(mDisposable!=null){
            mDisposable.dispose();
        }
    }

}
