package com.example.adapter.dingdan;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.bean.Result;
import com.example.bean.dingdan.DetailList;
import com.example.bean.dingdan.OrderList;
import com.example.myfirstp.R;
import com.example.myfirstp.ZhifuActivity;

import java.util.ArrayList;
import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.VH> {

    private Context context;

    public PaymentAdapter(Context context) {
        this.context = context;
    }

    private List<OrderList<List<DetailList>>> list = new ArrayList<>();

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)  {
        View view = LinearLayout.inflate(context, R.layout.wallpayone_item, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.ordernum.setText(list.get(i).getOrderId());
        vh.odernum.setText(list.get(i).getPayAmount()+"");
        vh.oderprice.setText(list.get(i).getPayAmount()+"");

        //订单详情  第二个Recycler
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false);
        vh.orderrecyler.setLayoutManager(linearLayoutManager);
        TowPaymentAdapter towPaymentAdapter = new TowPaymentAdapter(context);
        List<DetailList> detailList = list.get(i).getDetailList();

        towPaymentAdapter.addItem(detailList);
        vh.orderrecyler.setAdapter(towPaymentAdapter);
        vh.oderpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ZhifuActivity.class);
                intent.putExtra("orderId",list.get(0).getOrderId());
                context.startActivity(intent);

            }
        });
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
        private final TextView ordertime;//订单时间
        private final RecyclerView orderrecyler;//订单列表
        private final TextView odernum;//数量
        private final TextView oderprice;//总价
        private final Button odercancel;//取消订单
        private final Button oderpay;//确定付款

        public VH(@NonNull View itemView) {
            super(itemView);
            ordernum = itemView.findViewById(R.id.ordernum);
            ordertime = itemView.findViewById(R.id.ordertime);
            orderrecyler = itemView.findViewById(R.id.oderrecy2);
            odernum = itemView.findViewById(R.id.odernum);
            oderprice = itemView.findViewById(R.id.oderprice);
            odercancel = itemView.findViewById(R.id.odercancel);
            oderpay = itemView.findViewById(R.id.oderpay);
        }
    }
}
