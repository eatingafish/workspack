package com.example.presenter;

import com.example.core.IResult;
import com.example.core.NetWorkManager;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class RegPresenter extends BasePresenter{
    public RegPresenter(Consumer consumer) {
        super(consumer);
    }

    @Override
    protected Observable observable(Object... args) {
        IResult iResult = NetWorkManager.getNetWorkManager().create(IResult.class);
        return iResult.reg((String)args[0],(String)args[1]);
    }
}
