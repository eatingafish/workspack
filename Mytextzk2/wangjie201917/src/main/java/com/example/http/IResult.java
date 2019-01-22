package com.example.http;

import com.example.bean.Bannerbean;
import com.example.bean.DataInfo;
import com.example.bean.Result;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IResult {

    @FormUrlEncoded
    @POST("user/v1/login")
    Observable<Result<DataInfo>> login(@Field("phone") String phone, @Field("pwd") String pwd);

    @FormUrlEncoded
    @POST("user/v1/register")
    Observable<Result> reg(@Field("phone") String phone,@Field("pwd") String pwd);

    @GET("commodity/v1/bannerShow")
    Observable<Result<List<Bannerbean>>> banner();
}
