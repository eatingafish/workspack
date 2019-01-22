package com.example.frag;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adapter.RecyclerAdapter1;
import com.example.bean.Data;
import com.example.bean.Recycler1bean;
import com.example.presenter.ShowtextPresenter;
import com.example.wangjie201912.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;

public class Frag02 extends Fragment {
    @BindView(R.id.recycler1)
    RecyclerView recycler1;
    @BindView(R.id.recycler2)
    RecyclerView recycler2;
    Unbinder unbinder;
    private ShowtextPresenter showtextPresenter;
    private RecyclerAdapter1 recyclerAdapter1;
    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag02, container, false);
        unbinder = ButterKnife.bind(this, view);
        //适配器
        recyclerAdapter1 = new RecyclerAdapter1(getContext());
        //设置recyclerview
        linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recycler1.setLayoutManager(linearLayoutManager);
        recycler1.setAdapter(recyclerAdapter1);
        //设置recyclerview
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL, false);
        recycler2.setLayoutManager(gridLayoutManager);
        showtextPresenter = new ShowtextPresenter(new Successdata());
        showtextPresenter.request();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class Successdata implements Consumer<Recycler1bean<List<Data>>> {

        @Override
        public void accept(Recycler1bean<List<Data>> listRecycler1bean) throws Exception {
            List<Data> data = listRecycler1bean.getData();
            Log.e("wj","================data"+data.size());
            recyclerAdapter1.additem(data);
            recyclerAdapter1.notifyDataSetChanged();
        }
    }


    //内存泄漏
    @Override
    public void onDestroy() {
        super.onDestroy();
        showtextPresenter.unBind();
    }
}
