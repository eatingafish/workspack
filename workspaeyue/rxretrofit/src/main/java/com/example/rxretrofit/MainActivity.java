package com.example.rxretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Http.NetWorkManager;
import com.example.bean.Result;
import com.example.bean.UserInfo;
import com.example.core.ILogin;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.button_login)
    Button buttonLogin;
    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.button_login)
    public void onViewClicked() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.zhaoapi.cn/")//设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create())//数据解析器
                .build();

        ILogin iLogin = retrofit.create(ILogin.class);//相当于注解工具，吧相应的注解封装成Okhttp
        Call<Result<UserInfo>> resultCall = iLogin.login("13520965296","1234567");

        //异步处理
        resultCall.enqueue(new Callback<Result<UserInfo>>() {
            @Override
            public void onResponse(Call<Result<UserInfo>> call, Response<Result<UserInfo>> response) {
                Result<UserInfo> result = response.body();
                if (result.getCode().equals("0"))
                {
                    Toast.makeText(MainActivity.this, "请求成功", Toast.LENGTH_SHORT).show();
                    tv.setText(new Gson().toJson(result));
                }
            }

            @Override
            public void onFailure(Call<Result<UserInfo>> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.buttonRx_login)
    public void loginRx(){
        ILogin iLogin = NetWorkManager.getInstance().create(ILogin.class);

        iLogin.loginRx("13520965296","1234567")
                .subscribeOn(Schedulers.newThread())//将请求订阅到子线程上
                .observeOn(AndroidSchedulers.mainThread())//观察响应结果 把响应结果调度到主线程中处理
                .subscribe(new Consumer<Result<UserInfo>>() {//把响应结果订阅到消费者中处理
                    @Override
                    public void accept(Result<UserInfo> result) throws Exception {
                        if (result.getCode().equals("0"))
                        {
                            Toast.makeText(MainActivity.this, "请求成功RxAndroid", Toast.LENGTH_SHORT).show();
                        }
                        tv.setText("RxAndroid"+new Gson().toJson(result));
                    }
                });
    }
}
