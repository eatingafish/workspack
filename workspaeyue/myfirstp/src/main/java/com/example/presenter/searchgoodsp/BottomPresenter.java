package com.example.presenter.searchgoodsp;

import com.example.core.DataCall;
import com.example.http.IRequest;
import com.example.http.NetWorkManager;
import com.example.presenter.BasePresenter;

import io.reactivex.Observable;

public class BottomPresenter extends BasePresenter {
    public BottomPresenter(DataCall consumer) {
        super(consumer);
    }

    @Override
    protected Observable observable(Object... args) {

        IRequest iRequest = NetWorkManager.instance().create(IRequest.class);

        return iRequest.bottom((String) args[0]);
    }
}
