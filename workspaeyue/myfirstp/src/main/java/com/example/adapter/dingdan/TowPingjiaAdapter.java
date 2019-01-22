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

public class TowPingjiaAdapter extends RecyclerView.Adapter<TowPingjiaAdapter.VH> {

    private Context context;

    public TowPingjiaAdapter(Context context) {
        this.context = context;
    }

    private List<DetailList> list = new ArrayList<>();

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LinearLayout.inflate(viewGroup.getContext(), R.layout.pingjia_two, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.pingjianame.setText(list.get(i).getCommodityName());
        vh.pingjiaPrice.setText("￥"+list.get(i).getCommodityPrice()+"");
        String[] split = list.get(i).getCommodityPic().split(",");
        vh.pingjiasdv.setImageURI(Uri.parse(split[i]));
        Log.e("wj","====================TowPingjiaAdapter待评价商品ID"+list.get(i).getCommodityId());
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

        private final TextView pingjianame;
        private final TextView pingjiaPrice;
        private final SimpleDraweeView pingjiasdv;

        public VH(@NonNull View itemView) {
            super(itemView);
            pingjianame = itemView.findViewById(R.id.pingjiaName);
            pingjiaPrice = itemView.findViewById(R.id.pingjiaPrice);
            pingjiasdv = itemView.findViewById(R.id.pingjiasdv);
        }
    }
}
