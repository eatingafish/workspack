package com.example.adapter.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bean.Result;
import com.example.bean.searchgoods.SearchGoods;
import com.example.myfirstp.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchReccyclerAdapter extends XRecyclerView.Adapter<SearchReccyclerAdapter.VH> {

    private Context context;

    public SearchReccyclerAdapter(Context context) {
        this.context = context;
    }

    private ArrayList<SearchGoods> list = new ArrayList<>();

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LinearLayout.inflate(context, R.layout.search_item, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.searchiv.setImageURI(list.get(i).getMasterPic());
        vh.searchtv1.setText(list.get(i).getCommodityName());
        vh.searchtv2.setText(list.get(i).getPrice()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void additem(List<SearchGoods> result) {
        if (result != null)
        {
            list.addAll(result);
        }
    }

    public void removeAll() {
        list.clear();
    }

    public class VH extends RecyclerView.ViewHolder {

        private final SimpleDraweeView searchiv;
        private final TextView searchtv1;
        private final TextView searchtv2;

        public VH(@NonNull View itemView) {
            super(itemView);
            searchiv = itemView.findViewById(R.id.search_iv);
            searchtv1 = itemView.findViewById(R.id.search_tv1);
            searchtv2 = itemView.findViewById(R.id.search_tv2);
        }
    }
}
