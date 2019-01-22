package com.example.httputils;

import com.example.bean.Goodsbean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IResult {

    @FormUrlEncoded
    @POST("product/getProductDetail")
    Observable<Goodsbean> show(@Field("pid") String pid);
}
