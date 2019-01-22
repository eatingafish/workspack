package com.example.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bean.CommodityList;
import com.example.wangjie2019.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间：2019/1/14
 * 作者:王洁
 * 功能：展示第一模块的适配器
 */
public class ShowoneAdapter extends RecyclerView.Adapter<ShowoneAdapter.VH> {

    private Context context;

    public ShowoneAdapter(Context context) {
        this.context = context;
    }

    private ArrayList<CommodityList> list = new ArrayList<>();


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LinearLayout.inflate(context, R.layout.onerecycler_item, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.rximage.setImageURI(Uri.parse(list.get(i).getMasterPic()));
        vh.rxname.setText(list.get(i).getCommodityName());
        vh.rxprice.setText("￥"+list.get(i).getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(List<CommodityList> commodityList) {
        if (commodityList != null)
        {
            list.addAll(commodityList);
        }
    }

    public class VH extends RecyclerView.ViewHolder {

        private final SimpleDraweeView rximage;
        private final TextView rxname;
        private final TextView rxprice;

        public VH(@NonNull View itemView) {
            super(itemView);
            rximage = itemView.findViewById(R.id.rximage);
            rxname = itemView.findViewById(R.id.rxname);
            rxprice = itemView.findViewById(R.id.rxprice);
        }
    }
}
