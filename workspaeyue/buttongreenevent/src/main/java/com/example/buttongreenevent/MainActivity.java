package com.example.buttongreenevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.but)
    Button but;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.but2)
    Button but2;
    @BindView(R.id.but3)
    Button but3;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.imagview)
    ImageView imagview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //注册订阅者
        EventBus.getDefault().register(this);

    }

    @OnClick({R.id.but, R.id.btn1, R.id.but2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.but:
                Toast.makeText(this, "注解点击", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().postSticky("123");
                startActivity(new Intent(this,SecActivity.class));
                break;
            case R.id.btn1:
                Toast.makeText(this, "注解点击1111", Toast.LENGTH_SHORT).show();
                break;
            case R.id.but2:
                break;
        }
    }
    //定义处理接受的方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void hhhh(String hh)
    {

        text.setText(hh.toString());
    }


    //定义处理接受的方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void userEventBus(MessageEvent messageEvent)
    {
      text.setText(messageEvent.toString());
    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void receiveEventBus(StickyEvent stickyEvent){
        text.setText(stickyEvent.msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销注册
        EventBus.getDefault().unregister(this);
    }
}
