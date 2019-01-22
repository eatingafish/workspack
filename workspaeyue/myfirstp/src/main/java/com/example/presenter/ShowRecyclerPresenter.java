package com.example.presenter;

import com.example.core.DataCall;
import com.example.http.IRequest;
import com.example.http.NetWorkManager;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class ShowRecyclerPresenter extends BasePresenter{


    public ShowRecyclerPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NetWorkManager.instance().create(IRequest.class);
        return iRequest.home();
    }


}
