package com.example.http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWorkManager {

    private static NetWorkManager instance;
    private Retrofit retrofit;

    public NetWorkManager() {

    }
    //单例模式
    public static NetWorkManager getinstance(){
        if (instance == null)
        {
            instance = new NetWorkManager();
        }
        return instance;
    }

    private void init(){
        retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())//把响应结果的解析器
                .build();
    }

}
