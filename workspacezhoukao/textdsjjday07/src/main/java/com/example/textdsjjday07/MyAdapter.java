package com.example.textdsjjday07;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> list = new ArrayList<>();

    public MyAdapter(Context context) {
        this.context = context;
    }

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
        ViewHolder vh = null;
        if (convertView == null)
        {
            vh = new ViewHolder();
           convertView = View.inflate(context, R.layout.layout_item, null);
            vh.tv1 = convertView.findViewById(R.id.textview);
            vh.iv = convertView.findViewById(R.id.imageview1);
            convertView.setTag(vh);
        }
        else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tv1.setText("北京稻香村 稻香村中秋节月饼 老北京月饼礼盒655g");
        vh.tv2.setText("优惠价:￥111.99");
        return convertView;
    }
    class ViewHolder
    {
        TextView tv1;
        TextView tv2;
        ImageView iv;
    }
}
