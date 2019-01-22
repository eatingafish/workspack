package com.example.presemter;

import com.example.http.IResult;
import com.example.http.NetWorkManager;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class ListViewPresenter extends BasePresenter{

    public ListViewPresenter(Consumer consumer) {
        super(consumer);
    }

    @Override
    protected Observable observable(Object... args) {
        IResult iResult = NetWorkManager.getNetWorkManager().create(IResult.class);
        return iResult.listv((int) args[0]);
    }
}
