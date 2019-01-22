package com.example.presenter;

import com.example.bean.Result;
import com.example.core.DataCall;
import com.example.http.IRequest;
import com.example.http.NetWorkManager;

import io.reactivex.Observable;

public class RequestPresenter extends BasePresenter{


    public RequestPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NetWorkManager.instance().create(IRequest.class);
        return iRequest.register((String) args[0],(String) args[1]);
    }
}
