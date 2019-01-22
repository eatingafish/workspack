package com.example.textdsjjday14;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.bean.Bannerbean;
import com.example.bean.Result;
import com.example.presenter.BannerPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.banner)
    Banner banner;
    private BannerPresenter bannerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bannerPresenter = new BannerPresenter(new getDate());
        bannerPresenter.request();

    }

    private class getDate implements Consumer<Result<List<Bannerbean>>> {
        @Override
        public void accept(Result<List<Bannerbean>> listResult) throws Exception {
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
            final ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < listResult.getData().size(); i++) {
                String icon = listResult.getData().get(i).getIcon();
                list.add(icon);
            }
            banner.setImages(list);
            banner.start();
        }
    }
}
