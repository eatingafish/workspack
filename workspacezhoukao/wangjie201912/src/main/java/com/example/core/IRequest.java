package com.example.core;

import com.example.bean.Data;
import com.example.bean.Recycler1bean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IRequest {
    //@FormUrlEncoded
    @POST("product/getCatagory")
    Observable<Recycler1bean<List<Data>>> textshow();
}
