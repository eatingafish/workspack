package com.example.greendaowork;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class DayAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<dayStep> list;

    public DayAdapter(Context context, ArrayList<dayStep> list) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public dayStep getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null)
        {
            vh = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_list, null);
            vh.tv = convertView.findViewById(R.id.textView);
            vh.tv2 = convertView.findViewById(R.id.textView2);
            vh.tv3 = convertView.findViewById(R.id.textView3);
            convertView.setTag(vh);
        }
        else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tv.setText(String.valueOf(list.get(position).getId()));
        vh.tv2.setText(list.get(position).getName());
        vh.tv3.setText(list.get(position).getAge());
        return convertView;
    }

    public void removeday(dayStep item) {
        if (item != null)
        {
            list.remove(item);
        }
    }

    public void addUser(dayStep daysstep) {
        if (daysstep != null)
        {
            list.add(daysstep);
        }
    }

    public void addList(List<dayStep> daySteps) {
        if (daySteps != null)
        {
            list.addAll(daySteps);
        }
    }

    class ViewHolder{
        TextView tv;
        TextView tv2;
        TextView tv3;

}
}
