package com.example.core;

import com.example.bean.Result;
import com.example.bean.UserInfo;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ILogin {

    @POST("user/login")
    @FormUrlEncoded
    Call<Result<UserInfo>> login(@Field("mobile")String mobile,
                                 @Field("password")String password);

    @POST("user/login")
    @FormUrlEncoded
    Observable<Result<UserInfo>> loginRx(@Field("mobile")String mobile,
                                         @Field("password")String password);

}
