package com.example.presenter;

import com.example.httputils.IResult;
import com.example.httputils.NetWorkManager;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class MyPresenter extends BasePresenter{
    public MyPresenter(Consumer consumer) {
        super(consumer);
    }

    @Override
    protected Observable observable(Object... args) {
        IResult iResult = NetWorkManager.getInstance().create(IResult.class);
        return iResult.show(String.valueOf(args[0]));
    }
}
