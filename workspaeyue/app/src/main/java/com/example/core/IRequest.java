package com.example.core;

import com.example.bean.Result;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IRequest {
    @FormUrlEncoded
    @POST("product/searchProducts")
    Observable<Result> register(@Field("keywords") String keywords, @Field("page") String page);
}
