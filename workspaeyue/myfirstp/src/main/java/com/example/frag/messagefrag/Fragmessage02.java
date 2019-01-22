package com.example.frag.messagefrag;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bean.GoosMessage.GoodsMessageBean;
import com.example.bean.Result;
import com.example.core.DataCall;
import com.example.core.exception.ApiException;
import com.example.myfirstp.R;
import com.example.presenter.ProductDetalis.ProductDatilPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Fragmessage02 extends Fragment {
    @BindView(R.id.myMessagebitmap1)
    SimpleDraweeView myMessagebitmap1;
    @BindView(R.id.myMessageName)
    TextView myMessageName;
    @BindView(R.id.myMessagebitmap2)
    SimpleDraweeView myMessagebitmap2;
    Unbinder unbinder;
    private ProductDatilPresenter productDatilPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message02, container, false);
        unbinder = ButterKnife.bind(this, view);
        Intent intent = getActivity().getIntent();
        Bundle extras = intent.getExtras();
        int commodityId = extras.getInt("commodityId");
        productDatilPresenter = new ProductDatilPresenter(new getData());
        productDatilPresenter.reqeust(commodityId);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class getData implements DataCall<Result<GoodsMessageBean>> {
        @Override
        public void success(Result<GoodsMessageBean> data) {
            String[] split = data.getResult().getPicture().split(",");
            myMessagebitmap1.setImageURI(Uri.parse(split[1]));
            myMessagebitmap2.setImageURI(Uri.parse(split[2]));
            myMessageName.setText(data.getResult().getCommodityName());
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
