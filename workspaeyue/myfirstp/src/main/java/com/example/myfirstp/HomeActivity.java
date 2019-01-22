package com.example.myfirstp;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.frag.Frag_01;
import com.example.frag.Frag_02;
import com.example.frag.Frag_03;
import com.example.frag.Frag_04;
import com.example.frag.Frag_05;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.frag)
    FrameLayout frag;
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
        setContentView(R.layout.bottom);
        ButterKnife.bind(this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        final Frag_01 frag_01 = new Frag_01();
        final Frag_02 frag_02 = new Frag_02();
        final Frag_03 frag_03 = new Frag_03();
        final Frag_04 frag_04 = new Frag_04();
        final Frag_05 frag_05 = new Frag_05();
        transaction.add(R.id.frag, frag_01).hide(frag_02).hide(frag_03).hide(frag_04).hide(frag_05);
        transaction.add(R.id.frag, frag_02);
        transaction.add(R.id.frag, frag_03);
        transaction.add(R.id.frag, frag_04);
        transaction.add(R.id.frag, frag_05);
        transaction.commit();

        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                switch (checkedId)
                {
                    case R.id.radiobutton1:
                        transaction1.show(frag_01).hide(frag_02).hide(frag_03).hide(frag_04).hide(frag_05);
                        break;
                    case R.id.radiobutton2:
                        transaction1.show(frag_02).hide(frag_01).hide(frag_03).hide(frag_04).hide(frag_05);
                        break;
                    case R.id.radiobutton3:
                        transaction1.show(frag_03).hide(frag_02).hide(frag_01).hide(frag_04).hide(frag_05);
                        break;
                    case R.id.radiobutton4:
                        transaction1.show(frag_04).hide(frag_01).hide(frag_03).hide(frag_02).hide(frag_05);
                        break;
                    case R.id.radiobutton5:
                        transaction1.show(frag_05).hide(frag_01).hide(frag_03).hide(frag_04).hide(frag_02);
                        break;
                }
                transaction1.commit();
            }
        });

    }
}
