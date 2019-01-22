package com.example.textdajjday20;

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
        String ubtimap = "https://m.360buyimg.com/n0/jfs/t9004/210/1160833155/647627/ad6be059/59b4f4e1N9a2b1532.jpg";
        String ubtimap2 = "https://m.360buyimg.com/n0/jfs/t7504/338/63721388/491286/f5957f53/598e95f1N7f2adb87.jpg";
        String ubtimap3 = "https://m.360buyimg.com/n0/jfs/t9004/210/1160833155/647627/ad6be059/59b4f4e1N9a2b1532.jpg";
        String ubtimap4 = "https://m.360buyimg.com/n0/jfs/t7441/10/64242474/419246/adb30a7d/598e95fbNd989ba0a.jpg";

        ArrayList<String> list = new ArrayList<>();
        list.add(ubtimap);
        list.add(ubtimap2);
        list.add(ubtimap3);
        list.add(ubtimap4);

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

        banner.setImages(list);
        banner.start();
    }
}
