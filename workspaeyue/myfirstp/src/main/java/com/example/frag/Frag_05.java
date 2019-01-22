package com.example.frag;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bean.LoginReg.LoginInfo;
import com.example.dao.DaoMaster;
import com.example.dao.DaoSession;
import com.example.dao.LoginInfoDao;
import com.example.myfirstp.R;
import com.example.mypage.MyAddressActivity;
import com.example.mypage.MyMessageActivity;
import com.example.mypage.MyWalltActivity;
import com.example.mypage.MyquanziActivity;
import com.example.mypage.MyshezhiActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Frag_05 extends Fragment {
    @BindView(R.id.myPage_name)
    TextView myPageName;
    @BindView(R.id.myPage_geren)
    TextView myPageGeren;
    @BindView(R.id.myPage_quanzi)
    TextView myPageQuanzi;
    @BindView(R.id.myPage_foot)
    TextView myPageFoot;
    @BindView(R.id.myPage_wallt)
    TextView myPageWallt;
    @BindView(R.id.myPage_address)
    TextView myPageAddress;
    @BindView(R.id.myPage_hrad)
    SimpleDraweeView myPageHrad;
    @BindView(R.id.myPage_bg)
    RelativeLayout myPageBg;
    Unbinder unbinder;
    private Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_05, container, false);
        unbinder = ButterKnife.bind(this, view);
        //获取数据库
        DaoSession daoSession = DaoMaster.newDevSession(getContext(), LoginInfoDao.TABLENAME);
        LoginInfoDao loginInfoDao = daoSession.getLoginInfoDao();
        List<LoginInfo> loginInfos = loginInfoDao.loadAll();
        // Log.e("wj","=========数据库中的数据"+loginInfos.size());
        myPageName.setText(loginInfos.get(0).getNickName());
        myPageHrad.setImageURI(Uri.parse(loginInfos.get(0).getHeadPic()));
        return view;
    }

    //点击跳转到我的资料
    @OnClick(R.id.myPage_geren)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(),MyMessageActivity.class));
    }

    //点击跳转到我的钱包
    @OnClick(R.id.myPage_wallt)
    public void wallet()
    {
        startActivity(new Intent(getActivity(),MyWalltActivity.class));
    }

    //点击跳转到我的地址
    @OnClick(R.id.myPage_address)
    public void address()
    {
        startActivity(new Intent(getActivity(),MyAddressActivity.class));
    }

    //点击跳转到我的设置
    @OnClick(R.id.myPage_shezhi)
    public void shezhi()
    {
        startActivity(new Intent(getActivity(),MyshezhiActivity.class));
    }

    //点击跳转到我的圈子
    @OnClick(R.id.myPage_quanzi)
    public void quanzi()
    {
        startActivity(new Intent(getActivity(),MyquanziActivity.class));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
