package com.example.fresco_demo1;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private SimpleDraweeView sdv;
    private SimpleDraweeView sdv2;
    private SimpleDraweeView sdv3;
    private SimpleDraweeView sdv4;
    private SimpleDraweeView sdv5;
    private SimpleDraweeView sdv6;
    private File filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "http://d.hiphotos.baidu.com/image/pic/item/caef76094b36acaf6d1e855571d98d1000e99c98.jpg";
        //原始图片
         sdv = findViewById(R.id.sdv);
//        sdv.setImageURI(url);
        //圆形图片
        sdv2 = findViewById(R.id.sdv_2);
        sdv2.setImageURI(url);
        //带边框
        sdv3 = findViewById(R.id.sdv_3);
        sdv3.setImageURI(url);
        //圆角图片
        sdv4 = findViewById(R.id.sdv_4);
        sdv4.setImageURI(url);
        //底部圆角
        sdv5 = findViewById(R.id.sdv_5);
        sdv5.setImageURI(url);
        //高斯模糊
        sdv6 = findViewById(R.id.sdv_6);
        sdv6.setAspectRatio(0.7f);
        /**
         * 以高斯模糊显示。
         *
         * @param draweeView View。
         * @param url        url.
         * @param iterations 迭代次数，越大越魔化。
         * @param blurRadius 模糊图半径，必须大于0，越大越模糊。
         */

        try {
            Uri uri = Uri.parse(url);
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                    .setPostprocessor(new IterativeBoxBlurPostProcessor(1, 15))
                    .build();
            AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setOldController(sdv6.getController())
                    .setImageRequest(request)
                    .build();
            sdv6.setController(controller);
        } catch (Exception e) {
            e.printStackTrace();
        }

        loadFile(sdv, Environment.getExternalStorageDirectory().getPath()+"/Pictures/t.jpg",130,130);
       //loadFile(sdv, Environment.getExternalStorageDirectory().getPath() + "/Pictures/diqiu.jpg", 150, 150);
    }

    /**
     * 显示本地图片
     * @param draweeView
     * @param filePath
     * @param reqWidth
     * @param reqHeight
     */
    /**
     * 加载SDK图片
     */
    public static void loadFile(final SimpleDraweeView draweeView, String filePath, final int reqWidth, final int reqHeight) {
        Uri uri = new Uri.Builder()
                .scheme(UriUtil.LOCAL_FILE_SCHEME)
                .path(filePath)
                .build();
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setRotationOptions(RotationOptions.autoRotate())
                .setLocalThumbnailPreviewsEnabled(true)
                .setResizeOptions(new ResizeOptions(reqWidth, reqHeight))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(draweeView.getController())
                .setControllerListener(new BaseControllerListener<ImageInfo>() {
                    @Override
                    public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable anim) {
                        if (imageInfo == null) {
                            return;
                        }

                        ViewGroup.LayoutParams vp = draweeView.getLayoutParams();
                        vp.width = reqWidth;
                        vp.height = reqHeight;
                        draweeView.requestLayout();
                    }
                })
                .build();
        draweeView.setController(controller);
    }

}
