package com.example.frag;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.adapter.Showfrag01Adapter;
import com.example.adapter.Showfrag02Adapter;
import com.example.adapter.Showfrag03Adapter;
import com.example.bean.Bitmapbean;
import com.example.bean.Result;
import com.example.bean.showbean.ChanceBean;
import com.example.bean.showbean.HomeBean;
import com.example.bean.showbean.MessageBean;
import com.example.core.DataCall;
import com.example.core.exception.ApiException;
import com.example.myfirstp.R;
import com.example.myfirstp.SearchActivity;
import com.example.presenter.BitmapPresenter;
import com.example.presenter.ShowRecyclerPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 展示首页
 */
public class Frag_01 extends Fragment {
    @BindView(R.id.banner)
    MZBannerView banner;
    Unbinder unbinder;
    @BindView(R.id.recyclerH)
    RecyclerView recyclerH;
    @BindView(R.id.recyclerV)
    RecyclerView recyclerV;
    @BindView(R.id.recyclerG)
    RecyclerView recyclerG;
    @BindView(R.id.textsousuo)
    TextView textsousuo;
    private ShowRecyclerPresenter showRecyclerPresenter;
    private Showfrag01Adapter showfrag01Adapter;
    private BitmapPresenter bitmapPresenter;
    private ArrayList<String> listBitmap;
    private Showfrag02Adapter showfrag02Adapter;
    private Showfrag03Adapter showfrag03Adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        //Banner轮播图的presenter
        bitmapPresenter = new BitmapPresenter(new getBitmap());
        bitmapPresenter.reqeust();
        listBitmap = new ArrayList<>();//添加图片

        //热销商品适配器
        showfrag01Adapter = new Showfrag01Adapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerH.setLayoutManager(linearLayoutManager);
        showRecyclerPresenter = new ShowRecyclerPresenter(new getdata());
        showRecyclerPresenter.reqeust();
        recyclerH.setAdapter(showfrag01Adapter);


        //魔丽时尚适配器
        showfrag02Adapter = new Showfrag02Adapter(getContext());
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerV.setLayoutManager(linearLayoutManager2);
        showRecyclerPresenter = new ShowRecyclerPresenter(new getdata());
        recyclerV.setAdapter(showfrag02Adapter);

        //品质生活适配器
        showfrag03Adapter = new Showfrag03Adapter(getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerG.setLayoutManager(gridLayoutManager);
        showRecyclerPresenter = new ShowRecyclerPresenter(new getdata());
        recyclerG.setAdapter(showfrag03Adapter);
        return view;
    }

    /**
     * 点击跳转到搜索页面
     */
    @OnClick(R.id.textsousuo)
    public void onViewClicked()  {
        startActivity(new Intent(getActivity(),SearchActivity.class));
    }

    /**
     * 成功获取Banner
     */

    class getBitmap implements DataCall<Result<List<Bitmapbean>>> {
        @Override
        public void success(Result<List<Bitmapbean>> data) {
            List<Bitmapbean> result = data.getResult();
            banner.setIndicatorVisible(false);
            banner.setPages(result, new MZHolderCreator() {

                @Override
                public MZViewHolder createViewHolder() {
                    return new BannerViewHolder();
                }
            });
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    class BannerViewHolder implements MZViewHolder<Bitmapbean> {

        private SimpleDraweeView imagview;

        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            imagview = view.findViewById(R.id.simpbanner);
            return view;
        }

        @Override
        public void onBind(Context context, int i, Bitmapbean bitmapbean) {
            imagview.setImageURI(Uri.parse(bitmapbean.getImageUrl()));
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    class getdata implements DataCall<Result<HomeBean<List<ChanceBean<List<MessageBean>>>>>> {
        @Override
        public void success(Result<HomeBean<List<ChanceBean<List<MessageBean>>>>> data) {
            HomeBean<List<ChanceBean<List<MessageBean>>>> result = data.getResult();
            // 热销商品
            List<MessageBean> commodityList = result.getRxxp().get(0).getCommodityList();
            Log.e("wj","当前商品ID"+commodityList.get(0).getCommodityId());
            showfrag01Adapter.additem(commodityList);
            showfrag01Adapter.notifyDataSetChanged();

            //魔丽时尚
            List<MessageBean> commodityList2 = result.getMlss().get(0).getCommodityList();
            showfrag02Adapter.additem(commodityList2);
            showfrag02Adapter.notifyDataSetChanged();

            //品质生活
            List<MessageBean> commodityList3 = result.getPzsh().get(0).getCommodityList();
            showfrag03Adapter.additem(commodityList3);
            showfrag03Adapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
