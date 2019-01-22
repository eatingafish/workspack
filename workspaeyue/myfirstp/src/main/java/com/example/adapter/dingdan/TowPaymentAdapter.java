package com.example.adapter.dingdan;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bean.dingdan.DetailList;
import com.example.bean.dingdan.OrderList;
import com.example.myfirstp.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class TowPaymentAdapter extends RecyclerView.Adapter<TowPaymentAdapter.VH> {

    private Context context;

    public TowPaymentAdapter(Context context) {
        this.context = context;
    }

    private List<DetailList> list = new ArrayList<>();

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LinearLayout.inflate(viewGroup.getContext(), R.layout.wallpaytwo_item, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.twopayname.setText(list.get(i).getCommodityName());
        vh.twopayPrice.setText("￥"+list.get(i).getCommodityPrice()+"");
        String[] split = list.get(i).getCommodityPic().split(",");
        vh.twopaysdv.setImageURI(Uri.parse(split[i]));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(List<DetailList> detailList) {
        if (detailList != null)
        {
            list.addAll(detailList);
        }
    }

    public class VH extends RecyclerView.ViewHolder {

        private final TextView twopayname;
        private final TextView twopayPrice;
        private final SimpleDraweeView twopaysdv;

        public VH(@NonNull View itemView) {
            super(itemView);
            twopayname = itemView.findViewById(R.id.TwopaymentName);
            twopayPrice = itemView.findViewById(R.id.TwopaymentPrice);
            twopaysdv = itemView.findViewById(R.id.Twopaymentsdv);
        }
    }
}
