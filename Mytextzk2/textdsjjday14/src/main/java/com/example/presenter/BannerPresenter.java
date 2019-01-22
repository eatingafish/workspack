package com.example.presenter;

import com.example.http.IRequest;
import com.example.http.NetWorkMannger;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class BannerPresenter extends BasePresenter{
    public BannerPresenter(Consumer consumer) {
        super(consumer);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NetWorkMannger.getNetWorkMannger().create(IRequest.class);
        return iRequest.banner();
    }
}
