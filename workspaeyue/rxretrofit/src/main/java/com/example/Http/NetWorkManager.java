package com.example.Http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWorkManager {

    private static NetWorkManager mInstance;
    private  Retrofit retrofit;
    private static final String BASE_URI = "http://www.zhaoapi.cn/";
    private NetWorkManager(){
        init();
    }

    public static NetWorkManager getInstance(){
        if (mInstance == null)
        {
            synchronized (NetWorkManager.class){
                if (mInstance == null)
                {
                    mInstance = new NetWorkManager();
                    mInstance.init();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化必要对象和参数
     */
    public void init(){
        //初始化okhttp   okhttp里须要加拦截器所以要添加自定义的okhttp
      //  HttpLoggingInterceptor loging = new HttpLoggingInterceptor();
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        //初始化Retrofit
        retrofit = new Retrofit.Builder()
                .client(client)//添加了自定义的okhttp
                .baseUrl(BASE_URI)//设置uri
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//rxjava处理回调
                .addConverterFactory(GsonConverterFactory.create())//gson数据转换器
                .build();
    }

    public  Retrofit getRetrofit() {
        return retrofit;
    }

    //吧接口的注解翻译为okhttp请求
    public <T>T create(Class<T> service){
        return retrofit.create(service);
    }
}
