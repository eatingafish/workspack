package com.example.frag.messagefrag;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.adapter.messageAdapter.Message3_XRecyclerAdapter;
import com.example.bean.GoosMessage.ProductReviews;
import com.example.bean.Result;
import com.example.bean.TalkBean;
import com.example.core.DataCall;
import com.example.core.exception.ApiException;
import com.example.myfirstp.R;
import com.example.presenter.ProductDetalis.ProductReviewsPesenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Fragmessage03 extends Fragment {
    @BindView(R.id.message3_xrecycler)
    XRecyclerView message3Xrecycler;
    Unbinder unbinder;
    int page = 1;
    int count = 5;
    private ProductReviewsPesenter productReviewsPesenter;
    private Message3_XRecyclerAdapter message3_xRecyclerAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message03, container, false);
        unbinder = ButterKnife.bind(this, view);
        Intent intent = getActivity().getIntent();
        Bundle extras = intent.getExtras();
        int commodityId = extras.getInt("commodityId");
        //设置布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        message3Xrecycler.setLayoutManager(linearLayoutManager);
        message3_xRecyclerAdapter = new Message3_XRecyclerAdapter(getContext());
        productReviewsPesenter = new ProductReviewsPesenter(new review());
        productReviewsPesenter.reqeust(commodityId,1,999);

        //設置適配器
        message3Xrecycler.setAdapter(message3_xRecyclerAdapter);

        return view;
    }

     class review implements DataCall<Result<List<TalkBean>>>{

        @Override
        public void success(Result<List<TalkBean>> data) {
            message3_xRecyclerAdapter.addData(data);
            //Toast.makeText(getActivity(), "超过了"+size, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(getActivity(), ""+e.getCode(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
