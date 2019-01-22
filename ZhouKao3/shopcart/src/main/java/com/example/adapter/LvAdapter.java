package com.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bean.GoodsList;
import com.example.bean.Goodsbean;
import com.example.shopcart.R;

import java.util.ArrayList;
import java.util.List;


public class LvAdapter extends BaseAdapter {

    private Context context;

    public LvAdapter(Context context) {
        this.context = context;
    }

    private ArrayList<Goodsbean> list = new ArrayList<>();

    @Override
    public int getCount() {
        return list.size();

    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Vh vh = null;
        if (convertView == null){
            vh = new Vh();
            convertView = View.inflate(context, R.layout.list_title,null);
            vh.textView = convertView.findViewById(R.id.mTv_title);
            convertView.setTag(vh);
        }else{
            vh = (Vh) convertView.getTag();
        }
        vh.textView.setText(list.get(position).getSellerName());
        return convertView;
    }

    public void addItem(List<Goodsbean<List<GoodsList>>> data) {
        if (data != null)
        {
            list.addAll(data);
        }
    }


    class Vh{
        TextView textView;
    }
}
