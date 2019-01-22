package com.example.adapter.mypageAdapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bean.mypage.MyCirclebean;
import com.example.myfirstp.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class MyQuanziAdapter extends RecyclerView.Adapter<MyQuanziAdapter.VH> {

    private Context context;

    public MyQuanziAdapter(Context context) {
        this.context = context;
    }

    private List<MyCirclebean> list = new ArrayList<>();

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.mypage_recycler, viewGroup, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.mpagecontent.setText(list.get(i).getContent());
        vh.mpageimage.setImageURI(Uri.parse(list.get(i).getImage()));
      //  vh.mpagetime.setText(list.get(i).getCreateTime()+"");
        vh.mpagetext.setText(list.get(i).getGreatNum()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(List<MyCirclebean> result) {
        if (result != null)
        {
            list.addAll(result);
        }
    }

    public class VH extends RecyclerView.ViewHolder {

        private final TextView mpagecontent;
        private final SimpleDraweeView mpageimage;
        private final TextView mpagetext;
        private final CheckBox mpagedianz;
        private final TextView mpagetime;

        public VH(@NonNull View itemView) {
            super(itemView);
            mpagecontent = itemView.findViewById(R.id.mpage_content);
            mpageimage = itemView.findViewById(R.id.mpage_image);
            mpagetext = itemView.findViewById(R.id.mpage_greattext);
            mpagedianz = itemView.findViewById(R.id.mpage_dianzan);
            mpagetime = itemView.findViewById(R.id.mpage_time);
        }
    }
}
