package com.example.adapter.dingdan;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class OneReceivedAdapter extends RecyclerView.Adapter<OneReceivedAdapter.VH> {

    private Context context;

    public OneReceivedAdapter(Context context) {
        this.context = context;
    }

    private List<OrderList<List<DetailList>>> list = new ArrayList<>();

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)  {
        View view = LinearLayout.inflate(context, R.layout.shoukuanone, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.ordernum.setText(list.get(i).getOrderId()+"");
        Log.e("wj","OneReceivedAdapter订单ID"+list.get(i).getOrderId());
        vh.kuaididanhao.setText(list.get(i).getOrderId());
        vh.kuaitigongsi.setText(list.get(i).getExpressCompName());
        
        //订单详情  第二个Recycler
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false);
        vh.oderrecy2.setLayoutManager(linearLayoutManager);
        TowReceiverAdapter towReceiverAdapter = new TowReceiverAdapter(context);
        List<DetailList> detailList = list.get(i).getDetailList();

        towReceiverAdapter.addItem(detailList);
        vh.oderrecy2.setAdapter(towReceiverAdapter);
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
        private final TextView kuaitigongsi;//订单列表
        private final TextView kuaididanhao;
        private final Button oderpay;
        private final RecyclerView oderrecy2;


        public VH(@NonNull View itemView) {
            super(itemView);
            ordernum = itemView.findViewById(R.id.ordernum2);
            kuaitigongsi  = itemView.findViewById(R.id.kuaitigongsi);
            kuaididanhao = itemView.findViewById(R.id.kuaididanhao);
            oderpay = itemView.findViewById(R.id.oderpay);
            oderrecy2 = itemView.findViewById(R.id.oderrecy2);

        }
    }
}
