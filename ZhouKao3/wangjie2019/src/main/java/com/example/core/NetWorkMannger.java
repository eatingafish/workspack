package com.example.core;

import android.arch.lifecycle.ViewModelProvider;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 时间：2019/1/14
 * 作者:王洁
 * 功能：联网工具类
 */
public class NetWorkMannger {
    private static NetWorkMannger netWorkMannger = new NetWorkMannger();
    private Retrofit retrofit;

    private NetWorkMannger() {
        init ();
    }


    public static NetWorkMannger getNetWorkMannger() {
        if (netWorkMannger != null)
        {
            netWorkMannger = new NetWorkMannger();
        }
        return netWorkMannger;
    }

    private void init() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .connectTimeout(60,TimeUnit.SECONDS)
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
