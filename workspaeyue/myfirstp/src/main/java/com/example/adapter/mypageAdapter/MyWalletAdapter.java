package com.example.adapter.mypageAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bean.mypage.WalletBean;
import com.example.bean.mypage.WalletJia;
import com.example.myfirstp.R;

import java.util.ArrayList;

public class MyWalletAdapter extends BaseAdapter {
    private Context context;

    public MyWalletAdapter(Context context, ArrayList<WalletJia> list) {
        this.context = context;
        this.list = list;
    }

    private ArrayList<WalletJia> list = new ArrayList<>();

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.wallet_list, null);
            viewHolder.tv1 = convertView.findViewById(R.id.wallet_list_money);
            viewHolder.tv2 = convertView.findViewById(R.id.wallet_list_time);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv1.setText(list.get(position).getMoney());
        viewHolder.tv2.setText(list.get(position).getTime());
        return convertView;
    }

    /*public void addData(ArrayList<WalletJia> listt) {
        if (listt != null)
        {
            listt.addAll(listt);
        }*/
    }

    class ViewHolder{
        TextView tv1;
        TextView tv2;
    }


