package com.example.presenter;

import com.example.http.IResult;
import com.example.http.NetWorkMannger;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class RegPresenter extends BasePresenter{
    public RegPresenter(Consumer consumer) {
        super(consumer);
    }

    @Override
    protected Observable observable(Object... args) {
        IResult iResult = NetWorkMannger.getNetWorkMannger().create(IResult.class);
        return iResult.reg(String.valueOf(args[0]),String.valueOf(args[1]));
    }
}
