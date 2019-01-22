package com.example.presenter.circle;

import com.example.core.DataCall;
import com.example.http.IRequest;
import com.example.http.NetWorkManager;
import com.example.presenter.BasePresenter;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class CirclePresenter extends BasePresenter {

    private int page=1;

    public int getPage() {
        return page;
    }
    public CirclePresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NetWorkManager.instance().create(IRequest.class);
        boolean refresh = (boolean) args[0];
        if (refresh)
        {
            page = 1;
        }
        else {
            page++;
        }
        return iRequest.circle((long)args[1],(String)args[2],page,20);
    }
}
