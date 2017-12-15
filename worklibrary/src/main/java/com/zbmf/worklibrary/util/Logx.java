package com.zbmf.worklibrary.util;



import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;


/**
 * Created by xuhao on 2017/8/15.
 */

public class Logx {
    private static final String TAG="com.zbmf.StockGroup";
    private static boolean IS_DEBUG;
    public static void init(boolean isDebug){
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .tag(TAG)   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        IS_DEBUG=isDebug;
    }
    public static void e(String message){
        if(IS_DEBUG){
            Logger.e(message);
        }
    }
    public static void d(String o){
        if(IS_DEBUG){
            Logger.d(o);
        }
    }
    public static void i(String o){
        if(IS_DEBUG){
            Logger.i(o);
        }
    }
    public static void json(String json){
        if(IS_DEBUG){
            Logger.json(json);
        }
    }
}
