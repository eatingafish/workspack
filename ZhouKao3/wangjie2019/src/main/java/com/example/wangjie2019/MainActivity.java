package com.example.wangjie2019;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.adapter.ShowoneAdapter;
import com.example.adapter.ShowtwoAdapter;
import com.example.bean.AllDatabean;
import com.example.bean.BannerBean;
import com.example.bean.CommodityList;
import com.example.bean.Result;
import com.example.bean.RxxpBean;
import com.example.presenter.BannerPresenter;
import com.example.presenter.ShowPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
/**
 * 时间：2019/1/14
 * 作者:王洁
 * 功能：展示首页
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.Rexiaorecy)
    RecyclerView Rexiaorecy;
    @BindView(R.id.Molirecy)
    RecyclerView Molirecy;
    @BindView(R.id.Pinzhirecy)
    RecyclerView Pinzhirecy;
    private BannerPresenter bannerPresenter;
    private ShowPresenter showPresenter;
    private ShowoneAdapter showoneAdapter;
    private ShowtwoAdapter showtwoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bannerPresenter = new BannerPresenter(new getData());
        bannerPresenter.Request();

     //   热销新品
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        Rexiaorecy.setLayoutManager(linearLayoutManager);
        showPresenter = new ShowPresenter(new Showdata());
        showoneAdapter = new ShowoneAdapter(MainActivity.this);
        showPresenter.Request();
        Rexiaorecy.setAdapter(showoneAdapter);
    //魔力时尚
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        Molirecy.setLayoutManager(linearLayoutManager2);
        showPresenter = new ShowPresenter(new Showdata());
        showtwoAdapter = new ShowtwoAdapter(MainActivity.this);
        Molirecy.setAdapter(showtwoAdapter);
    }

    /**
     * 设置banner中的数据
     */
    private class getData implements Consumer<Result<List<BannerBean>>> {
        @Override
        public void accept(Result<List<BannerBean>> listResult) throws Exception {
            banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Uri parse = Uri.parse((String) path);
                    imageView.setImageURI(parse);
                }

                @Override
                public ImageView createImageView(Context context) {
                    SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
                    return simpleDraweeView;
                }
            });
            List<String> list = new ArrayList<>();
            for (int i = 0; i <listResult.getResult().size() ; i++) {
                String imageUrl = listResult.getResult().get(i).getImageUrl();
                list.add(imageUrl);
            }

            banner.setImages(list);
            banner.start();

        }

    }

    private class Showdata implements Consumer<Result<AllDatabean<List<RxxpBean<List<CommodityList>>>>>> {

        @Override
        public void accept(Result<AllDatabean<List<RxxpBean<List<CommodityList>>>>> allDatabeanResult) throws Exception {
            //热销
            List<CommodityList> commodityList = allDatabeanResult.getResult().getRxxp().get(0).getCommodityList();
            showoneAdapter.addItem(commodityList);
            //魔力
            List<CommodityList> commodityList2 = allDatabeanResult.getResult().getMlss().get(0).getCommodityList();
            showtwoAdapter.addItem(commodityList2);
        }
    }
}
