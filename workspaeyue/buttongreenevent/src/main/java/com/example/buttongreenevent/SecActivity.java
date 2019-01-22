package com.example.buttongreenevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecActivity extends AppCompatActivity {

    @BindView(R.id.sendData)
    Button sendData;
    @BindView(R.id.receive)
    Button receive;
    @BindView(R.id.tvreceive)
    TextView tvrecevie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.sendData, R.id.receive})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sendData:
                //发送事件
                EventBus.getDefault().post(new MessageEvent("Huhahha","123456"));
                finish();
                break;
            case R.id.receive:
                //要接受时开始注册
                EventBus.getDefault().register(SecActivity.this);
                EventBus.getDefault().postSticky(new StickyEvent("粘性事件"));
                startActivity(new Intent(SecActivity.this,MainActivity.class));
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void receiveEventBus(StickyEvent stickyEvent){
        tvrecevie.setText(stickyEvent.msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(SecActivity.this);
    }
}
