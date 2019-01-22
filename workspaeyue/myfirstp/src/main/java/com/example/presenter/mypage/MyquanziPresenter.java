package com.example.presenter.mypage;

import com.example.bean.Result;
import com.example.core.DataCall;
import com.example.http.IRequest;
import com.example.http.NetWorkManager;
import com.example.presenter.BasePresenter;

import io.reactivex.Observable;

public class MyquanziPresenter extends BasePresenter {
    public MyquanziPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NetWorkManager.instance().create(IRequest.class);
        return iRequest.quanzi((long) args[0],(String)args[1],(int)args[2],(int)args[3]);
    }
}
