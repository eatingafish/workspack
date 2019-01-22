package com.example.presenter.ProductDetalis;

import com.example.core.DataCall;
import com.example.http.IRequest;
import com.example.http.NetWorkManager;
import com.example.presenter.BasePresenter;

import io.reactivex.Observable;

/**
 * 商品評論
 */


public class ProductReviewsPesenter extends BasePresenter {
    public ProductReviewsPesenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NetWorkManager.instance().create(IRequest.class);
        return iRequest.reviews((int) args[0],(int) args[1],(int) args[2]);
    }
}
