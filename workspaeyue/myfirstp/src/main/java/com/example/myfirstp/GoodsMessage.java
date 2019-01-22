package com.example.myfirstp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.bean.LoginReg.LoginInfo;
import com.example.bean.Result;
import com.example.bean.shopcart.JoinShopbean;
import com.example.core.DataCall;
import com.example.core.exception.ApiException;
import com.example.dao.DaoMaster;
import com.example.dao.DaoSession;
import com.example.dao.LoginInfoDao;
import com.example.frag.messagefrag.Fragmessage01;
import com.example.frag.messagefrag.Fragmessage02;
import com.example.frag.messagefrag.Fragmessage03;
import com.example.presenter.shopcart.JoinShopCartPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 详情页面
 */
public class GoodsMessage extends AppCompatActivity {

    @BindView(R.id.image_message)
    ImageView imageMessage;
    @BindView(R.id.radiogroup_message)
    RadioGroup radiogroupMessage;
    @BindView(R.id.fragmessage)
    FrameLayout fragmessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_message);
        ButterKnife.bind(this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        final Fragmessage01 fragmessage01 = new Fragmessage01();
        final Fragmessage02 fragmessage02 = new Fragmessage02();
        final Fragmessage03 fragmessage03 = new Fragmessage03();
        transaction.add(R.id.fragmessage, fragmessage01).show(fragmessage01).hide(fragmessage02).hide(fragmessage03);
        transaction.add(R.id.fragmessage, fragmessage02);
        transaction.add(R.id.fragmessage, fragmessage03);
        transaction.commit();
        radiogroupMessage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                switch (checkedId) {
                    case R.id.radiobutton_message1:
                        transaction1.show(fragmessage01).hide(fragmessage02).hide(fragmessage03);
                        break;
                    case R.id.radiobutton_message2:
                        transaction1.show(fragmessage02).hide(fragmessage01).hide(fragmessage03);
                        break;
                    case R.id.radiobutton_message3:
                        transaction1.show(fragmessage03).hide(fragmessage02).hide(fragmessage01);
                        break;
                }
                transaction1.commit();
            }
        });

    }

}
