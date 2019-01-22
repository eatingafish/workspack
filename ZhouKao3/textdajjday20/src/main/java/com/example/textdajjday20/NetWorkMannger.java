package com.example.textdajjday20;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWorkMannger {
    private static NetWorkMannger netWorkMannger = new NetWorkMannger();
    private Retrofit retrofit;

    private NetWorkMannger() {
        init();
    }

    public NetWorkMannger getNetWorkMannger()
    {
        if (netWorkMannger == null)
        {
            netWorkMannger = new NetWorkMannger();
        }
        return netWorkMannger;
    }

    private void init() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://www.zhaoapi.cn/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public <T> T create(final Class<T> service) {
        return retrofit.create(service);

    }
}
