package com.example.core;

import com.example.bean.AllDatabean;
import com.example.bean.BannerBean;
import com.example.bean.CommodityList;
import com.example.bean.Result;
import com.example.bean.RxxpBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
/**
 * 时间：2019/1/14
 * 作者:王洁
 * 功能：接口
 */
public interface IRequest {
    /**
     * Banner轮播图
     * @return
     */
    @GET("commodity/v1/bannerShow")
    Observable<Result<List<BannerBean>>> banner();

    /**
     * 首页
     */
    @GET("commodity/v1/commodityList")
    Observable<Result<AllDatabean<List<RxxpBean<List<CommodityList>>>>>> rxxp();
}
