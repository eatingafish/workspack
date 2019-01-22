package com.example.wangjie201912;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.frag.HomeFrement;
import com.example.frag.Frag02;
import com.example.frag.Frag03;
import com.example.frag.Frag04;
import com.example.frag.Frag05;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.radiobutton1)
    RadioButton radiobutton1;
    @BindView(R.id.radiobutton2)
    RadioButton radiobutton2;
    @BindView(R.id.radiobutton3)
    RadioButton radiobutton3;
    @BindView(R.id.radiobutton4)
    RadioButton radiobutton4;
    @BindView(R.id.radiobutton5)
    RadioButton radiobutton5;
    @BindView(R.id.radiogroup)
    RadioGroup radiogroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        final HomeFrement homeFrement = new HomeFrement();
        final Frag02 frag02 = new Frag02();
        final Frag03 frag03 = new Frag03();
        final Frag04 frag04 = new Frag04();
        final Frag05 frag05 = new Frag05();
        transaction.add(R.id.frag, homeFrement).hide(frag02).hide(frag03).hide(frag04).hide(frag05);
        transaction.add(R.id.frag, frag02);
        transaction.add(R.id.frag, frag03);
        transaction.add(R.id.frag, frag04);
        transaction.add(R.id.frag, frag05);
        transaction.commit();
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                switch (checkedId)
                {
                    case R.id.radiobutton1:
                        transaction1.show(homeFrement).hide(frag02).hide(frag03).hide(frag04).hide(frag05);
                        break;
                    case R.id.radiobutton2:
                        transaction1.show(frag02).hide(homeFrement).hide(frag03).hide(frag04).hide(frag05);
                        break;
                    case R.id.radiobutton3:
                        transaction1.show(frag03).hide(homeFrement).hide(frag02).hide(frag04).hide(frag05);
                        break;
                    case R.id.radiobutton4:
                        transaction1.show(frag04).hide(homeFrement).hide(frag03).hide(frag02).hide(frag05);
                        break;
                    case R.id.radiobutton5:
                        transaction1.show(frag05).hide(homeFrement).hide(frag03).hide(frag04).hide(frag02);
                        break;
                }
                transaction1.commit();
            }
        });
    }
}
