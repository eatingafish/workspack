package com.example.http;

import com.example.bean.Bannerbean;
import com.example.bean.Result;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface IRequest {

    @GET("ad/getAd")
    Observable<Result<List<Bannerbean>>> banner();
}
