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
import com.example.myfirstp.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class TowReceiverAdapter extends RecyclerView.Adapter<TowReceiverAdapter.VH> {

    private Context context;

    public TowReceiverAdapter(Context context) {
        this.context = context;
    }

    private List<DetailList> list = new ArrayList<>();

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LinearLayout.inflate(viewGroup.getContext(), R.layout.shoukuantwo, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.showkuanName.setText(list.get(i).getCommodityName());
        vh.showkuanPrice.setText("￥"+list.get(i).getCommodityPrice()+"");
        String[] split = list.get(i).getCommodityPic().split(",");
        vh.showkuansdv.setImageURI(Uri.parse(split[i]));
        Log.e("wj","TowReceiverAdapter商品ID"+list.get(i).getCommodityId());
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

        private final TextView showkuanName;
        private final TextView showkuanPrice;
        private final SimpleDraweeView showkuansdv;

        public VH(@NonNull View itemView) {
            super(itemView);
            showkuanName = itemView.findViewById(R.id.showkuanName);
            showkuanPrice = itemView.findViewById(R.id.showkuanPrice);
            showkuansdv = itemView.findViewById(R.id.showkuansdv);
        }
    }
}
