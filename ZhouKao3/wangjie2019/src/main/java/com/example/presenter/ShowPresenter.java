package com.example.presenter;

import com.example.core.IRequest;
import com.example.core.NetWorkMannger;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class ShowPresenter extends BasePresenter{
    public ShowPresenter(Consumer consumer) {
        super(consumer);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NetWorkMannger.getNetWorkMannger().create(IRequest.class);
        return iRequest.rxxp();
    }
}
