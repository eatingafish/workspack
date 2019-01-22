package com.example.myfirstp;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.search.BottonAdapter;
import com.example.adapter.search.SearchReccyclerAdapter;
import com.example.adapter.search.TopAdapter;
import com.example.bean.Result;
import com.example.bean.searchgoods.BottomBean;
import com.example.bean.searchgoods.SearchGoods;
import com.example.bean.searchgoods.TopBean;
import com.example.core.DataCall;
import com.example.core.exception.ApiException;
import com.example.presenter.searchgoodsp.BottomPresenter;
import com.example.presenter.searchgoodsp.BtSearchPresenter;
import com.example.presenter.searchgoodsp.SearchByKeyPresenter;
import com.example.presenter.searchgoodsp.TopPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity implements XRecyclerView.LoadingListener {

    @BindView(R.id.image_lie)
    ImageView imageLie;
    @BindView(R.id.edit_search)
    EditText editSearch;
    @BindView(R.id.image_sou)
    ImageView imageSou;
    @BindView(R.id.image_nothing)
    ImageView imageNothing;
    @BindView(R.id.tv_nothing)
    TextView tvNothing;
    @BindView(R.id.recycler_search)
    XRecyclerView recyclerSearch;
    private SearchByKeyPresenter searchByKeyPresenter;
    private SearchReccyclerAdapter searchReccyclerAdapter;
    private TopPresenter topPresenter;
    private BottomPresenter bottomPresenter;
    private View popView;
    private TopAdapter topAdapter;
    private BottonAdapter bottonAdapter;
    int page = 1;
    private PopupWindow popupWindow;
    private BtSearchPresenter btSearchPresenter;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        //自定义搜索
        searchByKeyPresenter = new SearchByKeyPresenter(new getData());
        //获取pop第一层的数据
        topPresenter = new TopPresenter(new TopCall());
        topPresenter.reqeust();
        //获取POP第二层的数据
        bottomPresenter = new BottomPresenter(new BottomCall());
        bottomPresenter.reqeust(String.valueOf(1001002));
        //点击第二层按钮 分类搜索
        btSearchPresenter = new BtSearchPresenter(new BtSearchCall());
        //引入pop布局
        popView = View.inflate(this, R.layout.item_pop, null);
        //设置第一层RecyclerView布局
        RecyclerView mRv_Top = popView.findViewById(R.id.mRv_Top);
        topAdapter = new TopAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRv_Top.setLayoutManager(linearLayoutManager);
        topAdapter = new TopAdapter();
        mRv_Top.setAdapter(topAdapter);
        mRv_Top.setBackgroundColor(0x88000000);
        //设置第一层点击时间
        topAdapter.setOnClick(new TopAdapter.onClick() {
            @Override
            public void click(String id) {
                Toast.makeText(SearchActivity.this, "222", Toast.LENGTH_SHORT).show();
                bottomPresenter.reqeust(id);
            }
        });

        //设置第二层RecyclerView布局
        RecyclerView mRv_Bottom = popView.findViewById(R.id.mRv_Bottom);
        bottonAdapter = new BottonAdapter();
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRv_Bottom.setLayoutManager(linearLayoutManager1);
        mRv_Bottom.setAdapter(bottonAdapter);
        mRv_Bottom.setBackgroundColor(0x77000000);
        bottonAdapter.setOnClick(new BottonAdapter.onClick() {
            @Override
            public void chick(String id) {
                popupWindow.dismiss();
                Toast.makeText(SearchActivity.this, "222", Toast.LENGTH_SHORT).show();
                btSearchPresenter.reqeust(id, String.valueOf(page), "10");
            }
        });

        gridLayoutManager = new GridLayoutManager(this, 2,GridLayoutManager.VERTICAL,false);
        recyclerSearch.setLayoutManager(gridLayoutManager);
        searchReccyclerAdapter = new SearchReccyclerAdapter(this);
        recyclerSearch.setAdapter(searchReccyclerAdapter);
        recyclerSearch.setLoadingListener(this);
    }

    //新建pop
    @OnClick(R.id.image_lie)
    public void onViewClicked() {
        //新建Pop
        popupWindow = new PopupWindow(popView, 800, 200, true);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x000000));
        popupWindow.showAsDropDown(imageLie);
    }

    //点击搜索
    @OnClick(R.id.image_sou)
    public void sou(){
        String editkey = editSearch.getText().toString();
        searchByKeyPresenter.reqeust(editkey,1,999);
    }

    @Override
    public void onRefresh() {
        if (searchByKeyPresenter.setrun()) {
            recyclerSearch.refreshComplete();
            return;
        }
        String string = editSearch.getText().toString().trim();
        searchReccyclerAdapter.removeAll();
        searchReccyclerAdapter.notifyDataSetChanged();
        searchByKeyPresenter.reqeust(string,1,10);

    }

    @Override
    public void onLoadMore() {
        if (searchByKeyPresenter.setrun()) {
            recyclerSearch.loadMoreComplete();
            return;
        }

        String string = editSearch.getText().toString().trim();
        page++;
        searchByKeyPresenter.reqeust(string,2,10);
        recyclerSearch.loadMoreComplete();
    }

    private class getData implements DataCall<Result<List<SearchGoods>>> {
        @Override
        public void success(Result<List<SearchGoods>> data) {
            recyclerSearch.loadMoreComplete();
            recyclerSearch.refreshComplete();
            List<SearchGoods> result1 = data.getResult();
            if (result1.size() == 0)
            {
                recyclerSearch.setVisibility(View.GONE);
                tvNothing.setVisibility(View.VISIBLE);
                imageNothing.setVisibility(View.VISIBLE);
            }
            else {
                recyclerSearch.setVisibility(View.VISIBLE);
                tvNothing.setVisibility(View.GONE);
                imageNothing.setVisibility(View.GONE);
                searchReccyclerAdapter.removeAll();
                searchReccyclerAdapter.additem(result1);
                searchReccyclerAdapter.notifyDataSetChanged();
                recyclerSearch.refreshComplete();
                recyclerSearch.loadMoreComplete();
            }
        }
        @Override
        public void fail(ApiException e) {
            Toast.makeText(SearchActivity.this, "failed"+e.getCode(), Toast.LENGTH_SHORT).show();
        }
    }


    //获取Pop第一层的数据
    class TopCall implements DataCall<Result<List<TopBean>>> {
        @Override
        public void success(Result<List<TopBean>> data) {
            List<TopBean> result = data.getResult();
            topAdapter.addItem(result);
            topAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

        }
    }


    //获取Pop第第二层的数据
    class BottomCall implements DataCall<Result<List<BottomBean>>> {
        @Override
        public void success(Result<List<BottomBean>> data) {
            List<BottomBean> result = data.getResult();
            bottonAdapter.remove();
            bottonAdapter.addItem(result);
            bottonAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

            Toast.makeText(SearchActivity.this, "" + e.getCode(), Toast.LENGTH_SHORT).show();

        }
    }


    //自定义按钮 搜索数据
    class BtSearchCall implements DataCall<Result<List<SearchGoods>>> {
        @Override
        public void success(Result<List<SearchGoods>> data) {
            List<SearchGoods> result = data.getResult();
            searchReccyclerAdapter.removeAll();
            searchReccyclerAdapter.additem(result);
            searchReccyclerAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
