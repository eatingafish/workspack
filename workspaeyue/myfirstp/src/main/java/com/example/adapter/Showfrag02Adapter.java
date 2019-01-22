package com.example.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bean.showbean.HomeBean;
import com.example.bean.Result;
import com.example.bean.showbean.MessageBean;
import com.example.myfirstp.GoodsMessage;
import com.example.myfirstp.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class Showfrag02Adapter extends RecyclerView.Adapter<Showfrag02Adapter.VH> {

    private Context context;

    public Showfrag02Adapter(Context context) {
        this.context = context;
    }

    private ArrayList<MessageBean> list = new ArrayList<>();

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LinearLayout.inflate(context, R.layout.recyclelist_v, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, final int i) {
        vh.textv1.setText(list.get(i).getCommodityName());
        vh.textv2.setText("￥"+list.get(i).getPrice()+"");
        vh.imagviewv1.setImageURI(Uri.parse(list.get(i).getMasterPic()));
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoodsMessage.class);
                intent.putExtra("commodityId",list.get(i).getCommodityId());
                Log.e("wj","Showfrag02Adapter当前点击商品的ID"+list.get(i).getCommodityId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void additem(List<MessageBean> homeBeanGoodsResult) {

        if (homeBeanGoodsResult != null){
            list.addAll(homeBeanGoodsResult);
        }

    }

    public class VH extends RecyclerView.ViewHolder {

        private final SimpleDraweeView imagviewv1;
        private final TextView textv1;
        private final TextView textv2;

        public VH(@NonNull View itemView) {
            super(itemView);
            imagviewv1 = itemView.findViewById(R.id.imageviewv1);
            textv1 = itemView.findViewById(R.id.textviewv1);
            textv2 = itemView.findViewById(R.id.textviewv2);
        }
    }
}
