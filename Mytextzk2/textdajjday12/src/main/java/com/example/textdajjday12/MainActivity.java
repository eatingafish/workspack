package com.example.textdajjday12;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        banner = findViewById(R.id.banner);
        String btimap1= "www.zhaoapi.cn\\/images\\/quarter\\/ad1.png";
        String btimap2= "www.zhaoapi.cn\\/images\\/quarter\\/ad2.png";
        String btimap3= "www.zhaoapi.cn\\/images\\/quarter\\/ad3.png";
        String btimap4= "www.zhaoapi.cn\\/images\\/quarter\\/ad4.png";
        ArrayList<String> list = new ArrayList<>();
        list.add(btimap1);
        list.add(btimap2);
        list.add(btimap3);
        list.add(btimap4);
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
