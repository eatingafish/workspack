package com.example.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWorkManager {
    private static NetWorkManager instance;
    private Retrofit retrofit;

    public NetWorkManager() {
        init();
    }

    public static NetWorkManager instance() {
        if (instance == null)
        {
            instance = new NetWorkManager();
        }
        return instance;
    }

    private void init()
    {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new HttpLoggingInterceptor())
                .connectTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://172.17.8.100/small/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }

}
