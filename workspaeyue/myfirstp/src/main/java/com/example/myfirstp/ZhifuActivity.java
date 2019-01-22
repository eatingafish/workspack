package com.example.myfirstp;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.bean.LoginReg.LoginInfo;
import com.example.bean.Result;
import com.example.core.DataCall;
import com.example.core.exception.ApiException;
import com.example.dao.DaoMaster;
import com.example.dao.DaoSession;
import com.example.dao.LoginInfoDao;
import com.example.presenter.dingdan.ZhiFuPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhifuActivity extends AppCompatActivity {

    @BindView(R.id.pay_relative)
    RelativeLayout payRelative;
    @BindView(R.id.pay_image1)
    ImageView payImage1;
    @BindView(R.id.pay_my)
    RadioButton payMy;
    @BindView(R.id.pay_image2)
    ImageView payImage2;
    @BindView(R.id.pay_wx)
    RadioButton payWx;
    @BindView(R.id.pay_image3)
    ImageView payImage3;
    @BindView(R.id.pay_wzb)
    RadioButton payWzb;
    @BindView(R.id.pay_but_ok)
    Button payButOk;
    private String orderId;
    private String sessionId;
    private ZhiFuPresenter zhiFuPresenter;
    private int way = 1;
    private PopupWindow window_ok;
    private PopupWindow window_back;
    private View parent;
    private View inflate;
    private View inflate_back;
    private long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhifu);
        ButterKnife.bind(this);
        //得到穿过来的订单ID
        Intent intent = getIntent();
        orderId = intent.getStringExtra("orderId");
        double sum = intent.getDoubleExtra("sum", 0);
        //数据库
        DaoSession daoSession = DaoMaster.newDevSession(this, LoginInfoDao.TABLENAME);
        //获取操作数据库
        LoginInfoDao loginInfoDao = daoSession.getLoginInfoDao();
        List<LoginInfo> loginInfos = loginInfoDao.loadAll();
        for (int i = 0; i < loginInfos.size(); i++) {
            userId = loginInfos.get(i).getUserId();
            sessionId = loginInfos.get(i).getSessionId();
        }
        payButOk.setText("余额支付" + sum + "元");

        zhiFuPresenter = new ZhiFuPresenter(new ZhifuCall());
        parent = View.inflate(this, R.layout.activity_zhifu, null);
        //支付成功
        inflate = View.inflate(this, R.layout.popu_pay_layout, null);
        window_ok = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        window_ok.setFocusable(true);
        window_ok.setTouchable(true);
        window_ok.setOutsideTouchable(true);
        window_ok.setBackgroundDrawable(new BitmapDrawable());
        setWindow_ok();
        //支付失败
        inflate_back = View.inflate(this, R.layout.popu_pay_back_layout, null);
        window_back = new PopupWindow(inflate_back, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        window_back.setFocusable(true);
        window_back.setTouchable(true);
        window_back.setOutsideTouchable(true);
        window_back.setBackgroundDrawable(new BitmapDrawable());
        getWindow_back();

        payMy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    payWx.setChecked(false);
                    payWzb.setChecked(false);
                    way = 1;
                }
            }
        });
        payWx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    payMy.setChecked(false);
                    payWzb.setChecked(false);
                    way = 2;
                }
            }
        });
        payWzb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    payWx.setChecked(false);
                    payMy.setChecked(false);
                    way = 3;
                }
            }
        });
    }

    private void getWindow_back() {
        Button popu_pay_back_finish = inflate_back.findViewById(R.id.popu_pay_back_finish);
        popu_pay_back_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window_back.dismiss();
            }
        });
    }

    private void setWindow_ok() {
        Button popu_pay_ok_back = inflate.findViewById(R.id.popu_pay_ok_back);
        Button popu_pay_ok_finish = inflate.findViewById(R.id.popu_pay_ok_finish);
        popu_pay_ok_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window_ok.dismiss();
                Intent intent = new Intent(ZhifuActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        popu_pay_ok_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window_ok.dismiss();
                finish();
            }
        });
    }

    @OnClick(R.id.pay_but_ok)
    public void onViewClicked() {
        //Log.i("abc", "onViewClicked: "+way);
        switch (way) {
            case 1:
                zhiFuPresenter.reqeust(userId, sessionId, orderId, way);
                break;
            case 2:
                window_back.showAtLocation(parent, Gravity.NO_GRAVITY, 0, 0);
                break;
            case 3:
                window_back.showAtLocation(parent, Gravity.NO_GRAVITY, 0, 0);
                break;
        }
    }

    class ZhifuCall implements DataCall<Result> {

        @Override
        public void success(Result result) {
            if (result.getStatus().equals("0000")) {
                window_ok.showAtLocation(parent, Gravity.NO_GRAVITY, 0, 0);
            }
            Toast.makeText(ZhifuActivity.this, "" + result.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
