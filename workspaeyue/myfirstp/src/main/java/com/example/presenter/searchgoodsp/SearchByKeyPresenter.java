package com.example.presenter.searchgoodsp;

import com.example.core.DataCall;
import com.example.http.IRequest;
import com.example.http.NetWorkManager;
import com.example.presenter.BasePresenter;

import io.reactivex.Observable;

public class SearchByKeyPresenter extends BasePresenter {
    public SearchByKeyPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NetWorkManager.instance().create(IRequest.class);
        return iRequest.searchbykey(String.valueOf(args[0]),(int)args[1],(int)args[2]);
    }
}
