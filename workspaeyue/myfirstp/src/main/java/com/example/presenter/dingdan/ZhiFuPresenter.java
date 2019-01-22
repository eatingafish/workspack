package com.example.presenter.dingdan;
import com.example.bean.Result;
import com.example.core.DataCall;
import com.example.http.IRequest;
import com.example.http.NetWorkManager;
import com.example.presenter.BasePresenter;

import io.reactivex.Observable;

public class ZhiFuPresenter extends BasePresenter {

    public ZhiFuPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable<Result> observable(Object... args) {
        IRequest iRequest = NetWorkManager.instance().create(IRequest.class);
        return iRequest.zhifu((Long) args[0],(String) args[1],(String) args[2],(int) args[3]);
    }
}
