package com.example.frag.messagefrag;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.messageAdapter.Message3_XRecyclerAdapter;
import com.example.bean.GoosMessage.GoodsMessageBean;
import com.example.bean.LoginReg.LoginInfo;
import com.example.bean.Result;
import com.example.bean.TalkBean;
import com.example.bean.shopcart.JoinShopbean;
import com.example.core.DataCall;
import com.example.core.exception.ApiException;
import com.example.dao.DaoMaster;
import com.example.dao.DaoSession;
import com.example.dao.LoginInfoDao;
import com.example.myfirstp.R;
import com.example.presenter.ProductDetalis.ProductDatilPresenter;
import com.example.presenter.ProductDetalis.ProductReviewsPesenter;
import com.example.presenter.shopcart.JoinShopCartPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Fragmessage01 extends Fragment {
    @BindView(R.id.detail_Banner)
    Banner detailBanner;
    @BindView(R.id.mTv_Price_Commodity)
    TextView mTvPriceCommodity;
    @BindView(R.id.mTv_Sales_Commodity)
    TextView mTvSalesCommodity;
    @BindView(R.id.mTv_Title_Commodity)
    TextView mTvTitleCommodity;
    @BindView(R.id.mTv_Weight_Commodity)
    TextView mTvWeightCommodity;
    @BindView(R.id.mSDv_Details01_Commodity)
    SimpleDraweeView mSDvDetails01Commodity;
    @BindView(R.id.mTv_Name_Commodity)
    TextView mTvNameCommodity;
    @BindView(R.id.mSDv_Details02_Commodity)
    SimpleDraweeView mSDvDetails02Commodity;
    @BindView(R.id.mTv_TalkNum_Commodity)
    TextView mTvTalkNumCommodity;
    @BindView(R.id.mRv_Commodity)
    RecyclerView mRvCommodity;
    Unbinder unbinder;
    @BindView(R.id.join_shopcart)
    ImageView joinShopcart;
    private ProductDatilPresenter productDatilPresenter;
    private Message3_XRecyclerAdapter message3_xRecyclerAdapter;
    private ProductReviewsPesenter productReviewsPesenter;
    private String s;
    private List<LoginInfo> loginInfos;
    private String sessionId;
    private JoinShopCartPresenter joinShopCartPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message01, container, false);
        unbinder = ButterKnife.bind(this, view);
        Intent intent = getActivity().getIntent();
        Bundle extras = intent.getExtras();
        int commodityId = extras.getInt("commodityId");
        productDatilPresenter = new ProductDatilPresenter(new getData());
        productDatilPresenter.reqeust(commodityId);

        //展示评论的适配器
        message3_xRecyclerAdapter = new Message3_XRecyclerAdapter(getContext());
        //设置默认布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRvCommodity.setLayoutManager(linearLayoutManager);
        productReviewsPesenter = new ProductReviewsPesenter(new getReview());
        productReviewsPesenter.reqeust(commodityId, 1, 999);
        mRvCommodity.setAdapter(message3_xRecyclerAdapter);

        //数据库
        DaoSession daoSession = DaoMaster.newDevSession(getContext(), LoginInfoDao.TABLENAME);
        LoginInfoDao loginInfoDao = daoSession.getLoginInfoDao();
        loginInfos = loginInfoDao.loadAll();

        sessionId = loginInfos.get(0).getSessionId();

        JoinShopbean joinShopbean = new JoinShopbean(commodityId, 1);
        List<JoinShopbean> joinShopbeans = new ArrayList<>();
        joinShopbeans.add(joinShopbean);
        Gson gson = new Gson();
        s = gson.toJson(joinShopbeans);

        joinShopCartPresenter = new JoinShopCartPresenter(new joinShop());

        return view;
    }

    //点击加入购物车
    @OnClick(R.id.join_shopcart)
    public void onViewClicked() {
        long userId = loginInfos.get(0).getUserId();
        joinShopCartPresenter.reqeust(userId,sessionId,s);
    }

    private class getData implements DataCall<Result<GoodsMessageBean>> {
        @Override
        public void success(Result<GoodsMessageBean> data) {
            //Banner图片
            String[] split = data.getResult().getPicture().split(",");
            ArrayList<String> listbanner = new ArrayList<>();
            for (int i = 0; i < split.length; i++) {
                listbanner.add(split[i]);
            }
            detailBanner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Uri parse = Uri.parse((String) path);
                    imageView.setImageURI(parse);
                }

                @Override
                public ImageView createImageView(Context context) {
                    SimpleDraweeView simpleDraweeView = new SimpleDraweeView(getContext());
                    return simpleDraweeView;
                }
            });
            detailBanner.setImages(listbanner);
            detailBanner.start();

            mTvPriceCommodity.setText("￥" + String.valueOf(data.getResult().getPrice()));
            mTvTitleCommodity.setText(data.getResult().getCommodityName());
            mTvSalesCommodity.setText(data.getResult().getCategoryId());
            mTvWeightCommodity.setText("重量 " + String.valueOf(data.getResult().getWeight()) + "kg");
            mSDvDetails01Commodity.setImageURI(Uri.parse(split[0]));
            mTvNameCommodity.setText(data.getResult().getCommodityName());
            mSDvDetails02Commodity.setImageURI(Uri.parse(split[1]));
            mTvTalkNumCommodity.setText("当前评论总数" + data.getResult().getCategoryId());

        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(getContext(), "" + e.getCode(), Toast.LENGTH_SHORT).show();
        }
    }

    private class getReview implements DataCall<Result<List<TalkBean>>> {

        @Override
        public void success(Result<List<TalkBean>> data) {
            message3_xRecyclerAdapter.addData(data);
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    //同步成功 加入购物车
    private class joinShop implements DataCall<Result> {
        @Override
        public void success(Result data) {
            Toast.makeText(getContext(), ""+data.getMessage() , Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(getContext(), "同步失败" + e.getCode(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
