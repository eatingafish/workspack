package com.example.presenter.mypage;

import com.example.bean.Result;
import com.example.core.DataCall;
import com.example.http.IRequest;
import com.example.http.NetWorkManager;
import com.example.presenter.BasePresenter;

import io.reactivex.Observable;

public class UpdatenamePresenter extends BasePresenter {
    public UpdatenamePresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable<Result> observable(Object... args) {
        IRequest iRequest = NetWorkManager.instance().create(IRequest.class);
        return iRequest.updatename((int) args[0],(String) args[1],(String) args[2]);
    }
}
