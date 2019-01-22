package com.example.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bean.GoodsList;
import com.example.bean.Goodsbean;
import com.example.shopcart.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.VH> {

    private Context context;

    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    private List<GoodsList>  list = new ArrayList<>();


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_item, viewGroup, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.image.setImageURI(Uri.parse(list.get(i).getImages()));
        vh.name.setText(list.get(i).getTitle());
        vh.num.setText(list.get(i).getSalenum());
        Log.e("wj","======数量"+list.get(i).getSalenum());
        vh.price.setText(list.get(i).getPrice()+"");
//        vh.butjia.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(List<GoodsList> listt) {
        if (listt != null)
        {
            list.addAll(listt);
        }
    }


    public class VH extends RecyclerView.ViewHolder {

        private final SimpleDraweeView image;
        private final Button butjia;
        private final Button butjian;
        private final TextView name;
        private final TextView num;
        private final TextView price;

        public VH(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.simple_image);
            butjia = itemView.findViewById(R.id.button_jia);
            butjian = itemView.findViewById(R.id.button_jian);
            name = itemView.findViewById(R.id.goods_name);
            num = itemView.findViewById(R.id.goods_num);
            price = itemView.findViewById(R.id.goods_price);
        }
    }
}
