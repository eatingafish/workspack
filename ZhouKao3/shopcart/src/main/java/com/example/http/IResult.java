package com.example.http;

import com.example.bean.Goodsbean;
import com.example.bean.GoodsList;
import com.example.bean.Result;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IResult {

    @GET("product/getCarts")
    Observable<Result<List<Goodsbean<List<GoodsList>>>>> listv(@Query("uid") int uid);

    @GET("product/getCarts")
    Observable<Result<List<Goodsbean<List<GoodsList>>>>> goods(@Query("uid") int uid);
}
