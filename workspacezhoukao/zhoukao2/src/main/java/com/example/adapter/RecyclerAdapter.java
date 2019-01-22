package com.example.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bean.Goodsbean;
import com.example.zhoukao2.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.VH> {

    private Context context;

    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    ArrayList<Goodsbean> list = new ArrayList<>();

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LinearLayout.inflate(context, R.layout.xrecycler_item, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        String[] split = list.get(i).getData().getImages().split("!");
        vh.imageview1.setImageURI(Uri.parse(split[0]));
        vh.tv1.setText(list.get(i).getData().getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void additem(Goodsbean dataBean) {

        if (dataBean != null)
        {
            list.add(dataBean);
        }

    }

    public class VH extends RecyclerView.ViewHolder {

        private final SimpleDraweeView imageview1;
        private final TextView tv1;

        public VH(@NonNull View itemView) {
            super(itemView);
            imageview1 = itemView.findViewById(R.id.image1);
            tv1 = itemView.findViewById(R.id.tv1);
        }
    }
}
