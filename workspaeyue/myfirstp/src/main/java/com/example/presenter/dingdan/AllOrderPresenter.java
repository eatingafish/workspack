package com.example.presenter.dingdan;

import com.example.bean.Result;
import com.example.core.DataCall;
import com.example.http.IRequest;
import com.example.http.NetWorkManager;
import com.example.presenter.BasePresenter;

import io.reactivex.Observable;

public class AllOrderPresenter extends BasePresenter {
    public AllOrderPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NetWorkManager.instance().create(IRequest.class);
        return iRequest.allOder((long)args[0],String.valueOf(args[1]),(int)args[2],(int)args[3],(int)args[4]);
    }
}
