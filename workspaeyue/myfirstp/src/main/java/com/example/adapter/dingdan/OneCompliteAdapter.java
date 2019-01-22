package com.example.adapter.dingdan;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bean.dingdan.DetailList;
import com.example.bean.dingdan.OrderList;
import com.example.myfirstp.R;

import java.util.ArrayList;
import java.util.List;

public class OneCompliteAdapter extends RecyclerView.Adapter<OneCompliteAdapter.VH> {

    private Context context;

    public OneCompliteAdapter(Context context) {
        this.context = context;
    }

    private List<OrderList<List<DetailList>>> list = new ArrayList<>();

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)  {
        View view = LinearLayout.inflate(context, R.layout.yiwancheng_one, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.ordernum.setText(list.get(i).getOrderId()+"");

        //订单详情  第二个Recycler
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false);
        vh.orderrecyler.setLayoutManager(linearLayoutManager);
        TowyiwanchengAdapter towyiwanchengAdapter = new TowyiwanchengAdapter(context);
        List<DetailList> detailList = list.get(i).getDetailList();

        towyiwanchengAdapter.addItem(detailList);
        vh.orderrecyler.setAdapter(towyiwanchengAdapter);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void additem(List<OrderList<List<DetailList>>> orderList) {
        if (orderList != null)
        {
            list.addAll(orderList);
        }
    }


    public class VH extends RecyclerView.ViewHolder {

        private final TextView ordernum;//订单号
        private final RecyclerView orderrecyler;//订单列表


        public VH(@NonNull View itemView) {
            super(itemView);
            ordernum = itemView.findViewById(R.id.ordernum5);
            orderrecyler = itemView.findViewById(R.id.oderrecy5);

        }
    }
}
