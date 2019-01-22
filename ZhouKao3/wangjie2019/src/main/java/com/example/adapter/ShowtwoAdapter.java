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
 * 功能：展示第二模块的适配器
 */
public class ShowtwoAdapter extends RecyclerView.Adapter<ShowtwoAdapter.VH> {

    private Context context;

    public ShowtwoAdapter(Context context) {
        this.context = context;
    }

    private ArrayList<CommodityList> list = new ArrayList<>();


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LinearLayout.inflate(context, R.layout.tworecyler_item, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.moliimage.setImageURI(Uri.parse(list.get(i).getMasterPic()));
        vh.moliname.setText(list.get(i).getCommodityName());
        vh.moliprice.setText("￥"+list.get(i).getPrice());
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

        private final SimpleDraweeView moliimage;
        private final TextView moliname;
        private final TextView moliprice;

        public VH(@NonNull View itemView) {
            super(itemView);
            moliimage = itemView.findViewById(R.id.moliimage);
            moliname = itemView.findViewById(R.id.moliname);
            moliprice = itemView.findViewById(R.id.moliprice);
        }
    }
}
