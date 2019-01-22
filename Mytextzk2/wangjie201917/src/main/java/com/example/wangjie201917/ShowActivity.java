package com.example.wangjie201917;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.bean.Bannerbean;
import com.example.bean.Result;
import com.example.presenter.BannerPresenter;
import com.example.presenter.BasePresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class ShowActivity extends AppCompatActivity {

    @BindView(R.id.banner)
    Banner banner;
    private BasePresenter basePresenter;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        basePresenter = new BannerPresenter(new getData());
        basePresenter.request();
        String btimap1 = "http://172.17.8.100/images/small/banner/cj.png";
        String btimap2 = "http://172.17.8.100/images/small/banner/hzp.png";
        String btimap3 = "http://172.17.8.100/images/small/banner/lyq.png";
        String btimap4 = "http://172.17.8.100/images/small/banner/px.png";
        String btimap5 = "http://172.17.8.100/images/small/banner/wy.png";
        list = new ArrayList<>();
        list.add(btimap1);
        list.add(btimap2);
        list.add(btimap3);
        list.add(btimap4);
        list.add(btimap5);
    }

    private class getData implements Consumer<Result<List<Bannerbean>>> {
        @Override
        public void accept(final Result<List<Bannerbean>> listResult) throws Exception {
            banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                   imageView.setImageURI(Uri.parse((String) path));
                }

                @Override
                public ImageView createImageView(Context context) {
                    SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
                    return simpleDraweeView;
                }
            });
            banner.setImages(list);
            banner.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        basePresenter.unBind();
    }
}
