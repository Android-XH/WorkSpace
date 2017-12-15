package com.zbmf.StocksMatch.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.zbmf.StocksMatch.activity.LoginActivity;
import com.zbmf.StocksMatch.activity.MatchDescActivity;
import com.zbmf.StocksMatch.activity.MatchDetailActivity;
import com.zbmf.StocksMatch.bean.MatchBean;
import com.zbmf.StocksMatch.constatns.IntentKey;

/**
 * Created by xuhao on 2017/11/29.
 */

public class ShowActivity {
    public static Intent intent=null;
    public static void StartActivity(Context context,Intent intent){
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        if(intent!=null){
            intent=null;
        }
    }
    public static void StartActivity(Activity activity,Intent intent){
        activity.startActivity(intent);
        if(intent!=null){
            intent=null;
        }
    }
    public static void StartActivityForResult(Activity activity,Intent intent,int code){
        activity.startActivityForResult(intent,code);
        if(intent!=null){
            intent=null;
        }
    }

    public static void showActivity(Context context, Class<?> c){
        intent=new Intent(context,c);
        StartActivity(context,intent);
    }

    public static void showActivity(Activity activity, Bundle aBundle, Class<?> c){
        Intent intent = new Intent(activity,c);
        if (null != aBundle) {
            intent.putExtras(aBundle);
        }
        StartActivity(activity,intent);
    }
    public static void showActivityForResult(Activity activity, Bundle aBundle,Class<?> c,int code){
        Intent intent = new Intent(activity,c);
        if (null != aBundle) {
            intent.putExtras(aBundle);
        }
       StartActivityForResult(activity,intent,code);
    }

    /**
     * 是否登录，未登录打开登录页面
     * @param activity
     * @return
     */
    public static boolean isLogin(Activity activity){
        if(TextUtils.isEmpty(MatchSharedUtil.AuthToken())){
            intent=new Intent(activity, LoginActivity.class);
            StartActivity(activity,intent);
            return false;
        }else{
            return true;
        }
    }

    /**
     * 比赛详情
     * 如果没有参赛打开比赛简介
     * @param activity
     * @param matchBean
     */
    public static void showMatchDetail(Activity activity,MatchBean matchBean){
        if(matchBean.getIs_match_player()){
            Bundle bundle=new Bundle();
            bundle.putSerializable(IntentKey.MATCHBEAN,matchBean);
            ShowActivity.showActivity(activity,bundle, MatchDetailActivity.class);
        }else{
            showMathchDesc(activity,matchBean);
        }
    }

    /**
     * 比赛简介页面
     * @param activity
     * @param matchBean
     */
    public static void showMathchDesc(Activity activity,MatchBean matchBean){
        Bundle bundle=new Bundle();
        bundle.putSerializable(IntentKey.MATCHBEAN,matchBean);
        ShowActivity.showActivity(activity,bundle, MatchDescActivity.class);
    }
}
