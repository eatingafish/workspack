package com.example.adapter.dingdan;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bean.dingdan.DetailList;
import com.example.bean.dingdan.OrderList;
import com.example.myfirstp.R;

import java.util.ArrayList;
import java.util.List;

public class OneEvaluateAdapter extends RecyclerView.Adapter<OneEvaluateAdapter.VH> {

    private Context context;

    public OneEvaluateAdapter(Context context) {
        this.context = context;
    }
    private List<OrderList<List<DetailList>>> list = new ArrayList<>();

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LinearLayout.inflate(context, R.layout.pingjia_one, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.ordernum3.setText(list.get(i).getOrderId());
        //订单详情  第二个Recycler
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false);
        vh.oderrecy4.setLayoutManager(linearLayoutManager);
        TowPingjiaAdapter towPingjiaAdapter = new TowPingjiaAdapter(context);
        List<DetailList> detailList = list.get(i).getDetailList();
        Log.e("wj","====================OneEvaluateAdapter待评价订单号"+list.get(i).getOrderId());
        towPingjiaAdapter.addItem(detailList);
        vh.oderrecy4.setAdapter(towPingjiaAdapter);

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
        public TextView ordernum3;
        public TextView pingjiaTime;
        public RecyclerView oderrecy4;

        public VH(@NonNull View itemView) {
            super(itemView);
        ordernum3 = itemView.findViewById(R.id.ordernum3);
        pingjiaTime = itemView.findViewById(R.id.pingjiaTime);
        oderrecy4 = itemView.findViewById(R.id.oderrecy4);
        }
    }



}
