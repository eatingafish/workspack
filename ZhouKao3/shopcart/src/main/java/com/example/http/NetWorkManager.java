package com.example.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWorkManager {
    private static NetWorkManager netWorkManager = new NetWorkManager();
    private Retrofit retrofit;

    public static NetWorkManager getNetWorkManager(){
        if (netWorkManager == null)
        {
            netWorkManager = new NetWorkManager();
        }
        return netWorkManager;
    }

    private NetWorkManager() {
        init();
    }

    private void init() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://www.zhaoapi.cn/")
                .build();
    }

    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }
}
