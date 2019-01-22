package com.example.zhoukao2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.adapter.RecyclerAdapter;
import com.example.bean.Goodsbean;
import com.example.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    private MyPresenter myPresenter;
    private RecyclerAdapter recyclerAdapter;

    int pid = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recycler.setLayoutManager(gridLayoutManager);
        myPresenter = new MyPresenter(new getData());
        myPresenter.request(pid);
        recyclerAdapter = new RecyclerAdapter(this);
        recycler.setAdapter(recyclerAdapter);
    }

     class getData implements Consumer<Goodsbean>{
         @Override
         public void accept(Goodsbean dataBean) throws Exception {
             recyclerAdapter.additem(dataBean);
         }
    }
}
