package com.example.frag.dingdan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bean.LoginReg.LoginInfo;
import com.example.dao.DaoMaster;
import com.example.dao.DaoSession;
import com.example.dao.LoginInfoDao;
import com.example.myfirstp.R;

import java.util.List;

public class Fragment_Allorders extends Fragment {

    private String sessionId;
    private long userId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentlist_fragment_allorders, container, false);

        //数据库
        DaoSession daoSession = DaoMaster.newDevSession(getContext(), LoginInfoDao.TABLENAME);
        LoginInfoDao loginInfoDao = daoSession.getLoginInfoDao();
        List<LoginInfo> loginInfos = loginInfoDao.loadAll();
        for (int i = 0; i < loginInfos.size(); i++) {
            userId = loginInfos.get(0).getUserId();
            sessionId = loginInfos.get(i).getSessionId();
        }

        Fragment_Payment fragment_payment = new Fragment_Payment();
        Fragment_Received fragment_received = new Fragment_Received();
        Fragment_Evaluate fragment_evaluate = new Fragment_Evaluate();
        Fragment_Complete fragment_complete = new Fragment_Complete();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment1,fragment_payment).show(fragment_payment);
        transaction.add(R.id.fragment2,fragment_received).show(fragment_received);
        transaction.add(R.id.fragment3,fragment_evaluate).show(fragment_evaluate);
        transaction.add(R.id.fragment4,fragment_complete).show(fragment_complete);
        transaction.commit();
        return view;
    }
}
