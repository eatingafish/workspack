package com.example.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bean.Data;
import com.example.bean.Recycler1bean;
import com.example.wangjie201912.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter1 extends RecyclerView.Adapter<RecyclerAdapter1.VH> {

    private Context context;

    public RecyclerAdapter1(Context context) {
        this.context = context;
    }

    private ArrayList<Data> list = new ArrayList<>();

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LinearLayout.inflate(context, R.layout.recycler_item1, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.textview.setText(list.get(i).getName());
        Log.e("wj","==================设置的值"+list.get(i).getName());
    }

    @Override
    public int getItemCount() {
        Log.e("wj","=============listsize"+list.size());
        return list.size();
    }

    public void additem(List<Data> data) {
        if (data != null)
        {
            list.addAll(data);
        }
    }

    public class VH extends RecyclerView.ViewHolder {

        private final TextView textview;

        public VH(@NonNull View itemView) {
            super(itemView);
            textview = itemView.findViewById(R.id.textview1);
        }
    }
}
