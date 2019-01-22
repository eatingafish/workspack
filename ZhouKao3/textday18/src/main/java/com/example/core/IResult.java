package com.example.core;

import com.example.bean.Result;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IResult {
    @GET("user/reg")
    Observable<Result> reg(@Query("mobile") String mobile,@Query("password") String password);
}
