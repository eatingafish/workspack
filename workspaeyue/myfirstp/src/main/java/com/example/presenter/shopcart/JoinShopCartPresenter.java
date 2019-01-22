package com.example.presenter.shopcart;

import com.example.core.DataCall;
import com.example.http.IRequest;
import com.example.http.NetWorkManager;
import com.example.presenter.BasePresenter;

import io.reactivex.Observable;

public class JoinShopCartPresenter extends BasePresenter {
    public JoinShopCartPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NetWorkManager.instance().create(IRequest.class);
        return iRequest.join((long)args[0],String.valueOf(args[1]),String.valueOf(args[2]));
    }
}
