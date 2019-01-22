package com.example.presenter.circle;

import com.example.bean.Result;
import com.example.core.DataCall;
import com.example.http.IRequest;
import com.example.http.NetWorkManager;
import com.example.presenter.BasePresenter;

import io.reactivex.Observable;

public class CanclegreatPresenter extends BasePresenter {
    public CanclegreatPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable<Result> observable(Object... args) {
        IRequest iRequest = NetWorkManager.instance().create(IRequest.class);
        return iRequest.notagree((int) args[0],(String) args[1],(int) args[2]);
    }
}
