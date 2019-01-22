package com.example.frag;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.adapter.CircleAdapter;
import com.example.bean.CircleBean;
import com.example.bean.LoginReg.LoginInfo;
import com.example.bean.Result;
import com.example.core.DataCall;
import com.example.core.exception.ApiException;
import com.example.dao.DaoMaster;
import com.example.dao.DaoSession;
import com.example.dao.LoginInfoDao;
import com.example.http.SpacingItemDecoration;

import com.example.myfirstp.FaBuQuanActivity;
import com.example.myfirstp.R;
import com.example.presenter.circle.ArgegreatPresenter;
import com.example.presenter.circle.CanclegreatPresenter;
import com.example.presenter.circle.CirclePresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Frag_02 extends Fragment implements XRecyclerView.LoadingListener {
    @BindView(R.id.circleRecycler)
    XRecyclerView circleRecycler;
    Unbinder unbinder;
    @BindView(R.id.add_circle)
    ImageView addCircle;

    private CircleAdapter circleAdapter;
    private CirclePresenter circlePresenter;
    private List<LoginInfo> loginInfos;
    private long userId;
    private String sessionId;
    private ArgegreatPresenter argegreatPresenter;
    private CanclegreatPresenter canclegreatPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_02, container, false);
        unbinder = ButterKnife.bind(this, view);
        //设置默认布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        circleRecycler.setLayoutManager(linearLayoutManager);

        int space = getResources().getDimensionPixelSize(R.dimen.dip_20);
        circleRecycler.addItemDecoration(new SpacingItemDecoration(space));
        circleRecycler.setLoadingListener(this);
        circleAdapter = new CircleAdapter(getContext());


        circleRecycler.setAdapter(circleAdapter);

        argegreatPresenter = new ArgegreatPresenter(new getdata());
        canclegreatPresenter = new CanclegreatPresenter(new getdata());
        //数据库
        DaoSession daoSession = DaoMaster.newDevSession(getContext(), LoginInfoDao.TABLENAME);
        LoginInfoDao loginInfoDao = daoSession.getLoginInfoDao();
        loginInfos = loginInfoDao.loadAll();
        userId = loginInfos.get(0).getUserId();
        sessionId = loginInfos.get(0).getSessionId();
        circlePresenter = new CirclePresenter(new getCircle());
        circleRecycler.refresh();
        return view;
    }

    @OnClick(R.id.add_circle)
    public void add()
    {
        startActivity(new Intent(getContext(),FaBuQuanActivity.class));

    }

    @Override
    public void onRefresh() {
        if (circlePresenter.setrun()) {
            circleRecycler.refreshComplete();
            return;
        }

        circlePresenter.reqeust(true, userId,
                sessionId
        );
    }

    @Override
    public void onLoadMore() {
        if (circlePresenter.setrun()) {
            circleRecycler.loadMoreComplete();
            return;
        }

        circlePresenter.reqeust(false, userId,
                sessionId);
    }

    /**
     * 回调接口
     */

    private class getCircle implements DataCall<Result<List<CircleBean>>> {
        @Override
        public void success(Result<List<CircleBean>> data) {
            circleRecycler.loadMoreComplete();
            circleRecycler.refreshComplete();
            if (data.getStatus().equals("0000")) {
                //添加列表并刷新
                if (circlePresenter.getPage() == 1) {
                    circleAdapter.clear();
                }

                List<CircleBean> dataa = data.getResult();
                circleAdapter.adddata(dataa);
                circleAdapter.notifyDataSetChanged();
                circleAdapter.setIsCheckLin(new CircleAdapter.IsCheckLin() {
                    @Override
                    public void onclick(boolean ischeck, int pid, int position) {
                        if (ischeck)
                        {
                            argegreatPresenter.reqeust((int)userId,sessionId,pid);

                        }
                        else {
                            canclegreatPresenter.reqeust((int)userId,sessionId,pid);
                        }
                    }
                });
            }

        }


        @Override
        public void fail(ApiException e) {
            circleRecycler.refreshComplete();
            circleRecycler.loadMoreComplete();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class getdata implements DataCall<Result> {
        @Override
        public void success(Result data) {

        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
