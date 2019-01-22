package com.example.adapter.dingdan;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bean.dingdan.DetailList;
import com.example.myfirstp.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class TowyiwanchengAdapter extends RecyclerView.Adapter<TowyiwanchengAdapter.VH> {

    private Context context;

    public TowyiwanchengAdapter(Context context) {
        this.context = context;
    }

    private List<DetailList> list = new ArrayList<>();

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LinearLayout.inflate(viewGroup.getContext(), R.layout.yiwancheng_two, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.yiwanchengName.setText(list.get(i).getCommodityName());
        vh.yiwanchngPrice.setText("￥"+list.get(i).getCommodityPrice()+"");
        String[] split = list.get(i).getCommodityPic().split(",");
        vh.yiwanchengsdv.setImageURI(Uri.parse(split[i]));
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

        private final TextView yiwanchengName;
        private final TextView yiwanchngPrice;
        private final SimpleDraweeView yiwanchengsdv;

        public VH(@NonNull View itemView) {
            super(itemView);
            yiwanchengName = itemView.findViewById(R.id.yiwanchengName);
            yiwanchngPrice = itemView.findViewById(R.id.yiwanchngPrice);
            yiwanchengsdv = itemView.findViewById(R.id.yiwanchengsdv);
        }
    }
}
