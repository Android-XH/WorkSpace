package com.zbmf.StocksMatch.api;

import com.zbmf.StocksMatch.constatns.Constans;
import com.zbmf.StocksMatch.constatns.SharedKey;
import com.zbmf.worklibrary.util.SharedpreferencesUtil;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by xuhao on 2017/11/22.
 */
class MapKeyComparator implements Comparator<String> {
    @Override
    public int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }
}

public class SendParam {
    private static Map<String, String> getTokenMap(String token) {
        Map<String, String> map = new HashMap<>();
        map.put(ParamsKey.TOKEN, token);
        return map;
    }
    private static Map<String, String> getAuthTokenMap() {
        Map<String, String> map = new HashMap<>();
        map.put(ParamsKey.AUTH_TOKEN, SharedpreferencesUtil.getInstance().getString(SharedKey.AUTH_TOKEN, ""));
        return map;
    }
    public static Map<String, String> getRequest(String method, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        if (method != null) {
            map.put(ParamsKey.METHOD, method);
        }
        return getRequest(map);
    }

    public static Map<String, String> getRequest(Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(ParamsKey.API_KEY, HostUrl.API_KEY);
        map.put(ParamsKey.DEVICE_TYPE, HostUrl.DEVICE_TYPE);
        map = sortMapByKey(map);
        String api_sig = HostUrl.API_SECRET;
        for (String key : map.keySet()) {
            api_sig += key + map.get(key);
        }
        map.put(ParamsKey.API_SIG, getMD5String(api_sig).toLowerCase());
        return map;
    }
    /**
     * 使用 Map按key进行排序
     *
     * @param map
     * @return
     */
    private static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }

    private static String getMD5String(String string) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.reset();

            messageDigest.update(string.getBytes("UTF-8"));

        } catch (NoSuchAlgorithmException e) {

            System.out.println("NoSuchAlgorithmException caught!");

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString();
    }

    /**
     * 登录
     *
     * @param username
     * @param pass
     * @return
     */
    public static Map<String, String> getLoginMap(String username, String pass) {
        Map<String, String> map = new HashMap<>();
        map.put(ParamsKey.USERNAME, username);
        map.put(ParamsKey.PASSWORD, pass);
        map.put(ParamsKey.CLIENT_ID, "abxd1234");
        return getRequest(Method.LOGIN, map);
    }

    /**
     * 获取推荐比赛
     */
    public static Map<String, String> getSupremeMatch() {
        Map<String, String> param = getAuthTokenMap();
        return getRequest(Method.SUPREME_MATCH,param);
    }

    /**
     * 首页广告轮播
     * @return
     */
    public static Map<String, String> getReconpic() {
        return getRequest(null, null);
    }
    /**
     * 获取操盘高手
     *
     * @return
     */
    public static Map<String, String> getTraders() {
        Map<String, String> map = getTokenMap(SharedpreferencesUtil.getInstance().getString(SharedKey.AUTH_TOKEN, ""));
        return getRequest(map);
    }

    /**
     * 获取比赛列表
     *
     * @return
     */
    public static Map<String, String> getMatchList(int page) {
        Map<String, String> map = getTokenMap(SharedpreferencesUtil.getInstance().getString(SharedKey.AUTH_TOKEN, ""));
        map.put(ParamsKey.PAGE,String.valueOf(page));
        map.put(ParamsKey.PER_PAGE,String.valueOf(Constans.PERPAGE));
        return getRequest(map);
    }
    /**
     * 获取比赛信息
     *
     * @return
     */
    public static Map<String, String> getMatchDetail(String match_id) {
        Map<String, String> param = getAuthTokenMap();
        param.put(ParamsKey.MATCH_ID,match_id);
        param.put(ParamsKey.USER_ID,SharedpreferencesUtil.getInstance().getString(SharedKey.USER_ID,""));
        return getRequest(Method.GETPLAYER,param);
    }
    public static Map<String, String> getHoldList(String match_id) {
        Map<String, String> param = getAuthTokenMap();
        param.put(ParamsKey.MATCH_ID,match_id);
        param.put(ParamsKey.USER_ID,SharedpreferencesUtil.getInstance().getString(SharedKey.USER_ID,""));
        param.put(ParamsKey.PAGE,String.valueOf(1));
        param.put(ParamsKey.PER_PAGE,String.valueOf(10));
        param.put(ParamsKey.HIDE,"1");
        param.put(ParamsKey.USER_ID,SharedpreferencesUtil.getInstance().getString(SharedKey.USER_ID,""));
        return getRequest(Method.GET_HOLD_LIST,param);
    }
    /**
     * 获取用户参加的比赛
     *
     * @return
     */
    public static Map<String, String> getRunMatchList() {
        Map<String, String> param = getAuthTokenMap();
        return getRequest(Method.GET_RUNMATCHS,param);
    }
    /**
     * 获取用户信息
     *
     * @return
     */
    public static Map<String, String> getUserInfo() {
        Map<String, String> param = getAuthTokenMap();
        return getRequest(Method.GET_USERINFO,param);
    }

    /**
     * 获取最新交易
     * @param page
     * @return
     */
    public static Map<String, String> dealSys(int page) {
        Map<String, String> param = getAuthTokenMap();
        param.put(ParamsKey.PAGE,String.valueOf(page));
        param.put(ParamsKey.PER_PAGE,String.valueOf(Constans.PERPAGE));
        return getRequest(Method.DEALSYS,param);
    }

    /**
     * 获取排行榜
     * @param match_id
     * @param order 0：全部 7：一周 1：一天 30：一个月
     * @param page
     * @return
     */
    public static Map<String,String>getYieldList(String match_id,String order,int page){
        Map<String, String> mParams = getAuthTokenMap();
        mParams.put(ParamsKey.MATCH_ID,match_id);
        mParams.put(ParamsKey.PAGE,String.valueOf(page));
        mParams.put(ParamsKey.PER_PAGE,String.valueOf(Constans.PERPAGE));
        mParams.put(ParamsKey.ORDER,order);
        return getRequest(Method.GET_MATCH_YIELD_LIST,mParams);
    }

    /**
     * 报名比赛
     * @param map
     * @return
     */
    public static Map<String,String>getApplyMatch(Map<String,String>map){
        map.put(ParamsKey.AUTH_TOKEN, SharedpreferencesUtil.getInstance().getString(SharedKey.AUTH_TOKEN, ""));
        return getRequest(Method.VERIFY_CODE,map);
    }
}

