package com.example.presenter;

import com.example.core.IRequest;
import com.example.utils.NetWorkManager;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class ShowtextPresenter extends BasePresenter{
    public ShowtextPresenter(Consumer consumer) {
        super(consumer);
    }

    @Override
    public Observable observable(Object... args) {
        IRequest iRequest = NetWorkManager.netWorkManager().create(IRequest.class);
        return iRequest.textshow();
    }
}
