package com.example.presemter;

import com.example.http.IResult;
import com.example.http.NetWorkManager;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class GoodsPresenter extends BasePresenter{
    public GoodsPresenter(Consumer consumer) {
        super(consumer);
    }


    @Override
    protected Observable observable(Object... args) {
        IResult iResult = NetWorkManager.getNetWorkManager().create(IResult.class);
        return iResult.goods((int) args[0]);
    }
}
