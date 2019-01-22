package com.example.shopcart;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.adapter.LvAdapter;
import com.example.adapter.RecyclerAdapter;
import com.example.bean.GoodsList;
import com.example.bean.Goodsbean;
import com.example.bean.Result;
import com.example.presemter.GoodsPresenter;
import com.example.presemter.ListViewPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mLv_Title)
    ListView mLvTitle;
    @BindView(R.id.mLv_Shop)
    RecyclerView mLvShop;
    private LvAdapter lvAdapter;
    private ListViewPresenter listViewPresenter;
    private RecyclerAdapter recyclerAdapter;
    private GoodsPresenter goodsPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mLvShop.setLayoutManager(linearLayoutManager);
        goodsPresenter = new GoodsPresenter(new getShou());
        recyclerAdapter = new RecyclerAdapter(this);
      //  goodsPresenter.Request(71);
        mLvShop.setAdapter(recyclerAdapter);

        listViewPresenter = new ListViewPresenter(new getData());
        lvAdapter = new LvAdapter(this);
        mLvTitle.setAdapter(lvAdapter);
        listViewPresenter.Request(72);
        mLvTitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //goodsPresenter.Request(position);
            }
        });


    }

    private class getData implements Consumer<Result<List<Goodsbean<List<GoodsList>>>>> {

        @Override
        public void accept(Result<List<Goodsbean<List<GoodsList>>>> listResult) throws Exception {
            List<Goodsbean<List<GoodsList>>> data = listResult.getData();
            lvAdapter.addItem(data);

        }
    }

//    private class getShou implements Consumer<Result<List<Goodsbean<List<GoodsList>>>>> {
//        @Override
//        public void accept(Result<List<Goodsbean<List<GoodsList>>>> listResult) throws Exception {
//            List<Goodsbean<List<GoodsList>>> data = listResult.getData();
//        }
//    }

    private class getShou implements Consumer<Result<List<Goodsbean<List<GoodsList>>>>> {

        @Override
        public void accept(Result<List<Goodsbean<List<GoodsList>>>> listResult) throws Exception {
            List<GoodsList> lists = listResult.getData().get(2).getList();
            recyclerAdapter.addItem(lists);
        }
    }
}
