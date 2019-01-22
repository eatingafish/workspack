package com.example.openactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    private WebView mwb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        mwb = findViewById(R.id.mwb);
        mwb.setWebViewClient(new WebViewClien());

        mwb.loadUrl("http://www.baidu.com/");
    }


    private class WebViewClien extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("-----------","拦截到网页的点击事件url = "+url);
            if (url != null && url.contains("https://wk.baidu.com/?statcms=index_wenku&ssid=0&from=844b&bd_page_type=1&uid=0&pu=sz%401321_1001%2Cta%40utouch_2_5.1_24_39.0&news?idx=30000&itj=311")) {
                Log.d("==========","拦截到需要的URL");
                //实现我们自己的处理，例如跳转到另一个Activity
                Intent intent = new Intent(WebViewActivity.this, ViewActivity.class);
                startActivity(intent);
                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            view.getSettings().setJavaScriptEnabled(true);
            super.onPageFinished(view, url);
        }
    }
}
