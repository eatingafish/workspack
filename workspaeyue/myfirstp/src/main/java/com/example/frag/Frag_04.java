package com.example.frag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.frag.dingdan.Fragment_Allorders;
import com.example.frag.dingdan.Fragment_Complete;
import com.example.frag.dingdan.Fragment_Evaluate;
import com.example.frag.dingdan.Fragment_Payment;
import com.example.frag.dingdan.Fragment_Received;
import com.example.myfirstp.R;

import java.util.ArrayList;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;


public class Frag_04 extends Fragment {
    private ViewPager orderViewPager;
    private ArrayList<Fragment> fragments;
    private RadioGroup radioGroupa;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);


        orderViewPager = view.findViewById(R.id.OrderViewPager);

        Fragment_Allorders fragment_allorders = new Fragment_Allorders();//全部订单
        Fragment_Payment fragment_pending = new Fragment_Payment();//待付款
        Fragment_Received fragment_received = new Fragment_Received();//待收货
        Fragment_Evaluate fragment_evaluate = new Fragment_Evaluate();//待评价
        Fragment_Complete fragment_complete = new Fragment_Complete();//已完成

        fragments = new ArrayList<>();
        fragments.add(fragment_allorders);
        fragments.add(fragment_pending);
        fragments.add(fragment_received);
        fragments.add(fragment_evaluate);
        fragments.add(fragment_complete);


        orderViewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });


        radioGroupa = (RadioGroup) view.findViewById(R.id.radioGroupa);
        radioGroupa.check(R.id.radioButtona);
        //页面切换按钮也跟着切换
        orderViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                radioGroupa.check(radioGroupa.getChildAt(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        //按钮切换页面也跟着切换
        radioGroupa.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.e("====================", checkedId + "");
                orderViewPager.setCurrentItem(checkedId-R.id.radioButtona);
            }
        });

        return view;
    }
}

