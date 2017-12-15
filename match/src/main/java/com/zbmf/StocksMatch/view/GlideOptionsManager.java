package com.zbmf.StocksMatch.view;

import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.zbmf.StocksMatch.R;

import com.zbmf.worklibrary.glide.GlideCircleTransform;

/**
 * Created by xuhao on 2017/7/18.
 */

public class GlideOptionsManager {

    private static GlideOptionsManager instance;

    private RequestOptions mOptions,mBanner;

    private GlideOptionsManager() {

    }


    public static GlideOptionsManager getInstance() {
        if(instance == null) {
            synchronized (GlideOptionsManager.class) {
                if(instance == null) {
                    instance = new GlideOptionsManager();
                }
            }
        }
        return instance;
    }

    public RequestOptions getRequestOptions() {
        if(mOptions == null) {
            mOptions = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .priority(Priority.HIGH)
                    .transform(new GlideCircleTransform());
        }
        return mOptions;
    }
    public RequestOptions getBannerOptions() {
        if(mBanner == null) {
            mBanner = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .priority(Priority.HIGH)
                    .transform(new GlideRoundTransform());
        }
        return mBanner;
    }
}
