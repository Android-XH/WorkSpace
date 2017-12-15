package com.zbmf.worklibrary.presenter;


import com.zbmf.worklibrary.model.BaseMode;
import com.zbmf.worklibrary.util.Logx;
import com.zbmf.worklibrary.baseview.BaseView;

import java.io.Serializable;

/**
 * Created by xuhao on 2017/11/21.
 */
public abstract class BasePresenter<T extends BaseMode,D extends BaseView> implements IBasePresenter,Serializable{
     private T mMode;
     private D mView;
     private boolean isFirst=true;
     public abstract void getDatas();
     public abstract T initMode();
     @Override
     public void onDestroy() {
          if(mMode!=null){
               mMode.onDestory();
               mMode=null;
          }
          if(mView!=null){
               mView=null;
          }
     }

     public boolean isFirst() {
          return isFirst;
     }

     public void setFirst(boolean first) {
          isFirst = first;
     }

     @Override
     public void onStart(BaseView view) {
          Logx.e("创建"+this.getClass().getName());
          mView= (D) view;
          mMode=initMode();
     }

     public T getMode() {
          return mMode;
     }


     public D getView() {
          return mView;
     }

}