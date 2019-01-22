package com.example.openactivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    WebView mWebView;
    Button button;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView =(WebView) findViewById(R.id.webview);
        WebSettings webSettings = mWebView.getSettings();
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        //把java对象传递给JS
        mWebView.addJavascriptInterface(this,"aa");
        // 先载入JS代码
        // 格式规定为:file:///android_asset/文件名.html
        mWebView.loadUrl("file:///android_asset/aa.html");
    }
    //增加JS注解，保证html中js代码能够调用
    @JavascriptInterface
    public void returnAndroid() {//从网页跳回到APP
        //这里写你的操作///////////////////////
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        startActivity(intent);
    }

}
