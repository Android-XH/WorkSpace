package com.zbmf.worklibrary.rxjava;



import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
//import zbmf.com.worklibrary.bean.BaseEntity;

/**
 * Created by xuhao on 2017/6/12.
 */
public interface RetrofitService {
    @GET()
    Observable<Object> get(@Url String url ,@QueryMap Map<String, String> map);

    @POST()
    @FormUrlEncoded
    Observable<Object> post(@Url String url,@Query("method") String method, @FieldMap Map<String, String> map);
}