package com.example.adapter.messageAdapter;

import android.content.Context;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bean.GoosMessage.ProductReviews;
import com.example.bean.Result;
import com.example.bean.TalkBean;
import com.example.myfirstp.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 详情页面 ----评论适配器
 */
public class Message3_XRecyclerAdapter extends XRecyclerView.Adapter<Message3_XRecyclerAdapter.XVH> {

    private Context context;
    private ArrayList<TalkBean> list = new ArrayList<>();
    public Message3_XRecyclerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public XVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LinearLayout.inflate(context, R.layout.message3_xrecyclerview, null);
        return new XVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull XVH xvh, int i) {

        xvh.messagehead.setImageURI(list.get(i).getHeadPic());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
        Date date = new Date(list.get(i).getCreateTime());        //获取当前时间
        xvh.messagetime.setText(String.valueOf(simpleDateFormat.format(date)));//时间
       // Log.e("wj","=============评论时间"+date);
        xvh.messagename.setText(list.get(i).getNickName());
       // Log.e("wj","==============姓名"+list.get(i).getNickName());
        xvh.messagecontent.setText(list.get(i).getContent());
        Glide.with(context).load(list.get(i).getImage()).into(xvh.messageimage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(Result<List<TalkBean>> data) {
        if (data != null)
        {
            list.addAll(data.getResult());
        }
    }


    /*public void addData(Result<List<TalkBean>> result) {

    }*/

    public class XVH extends XRecyclerView.ViewHolder {

        private final SimpleDraweeView messagehead;
        private final TextView messagename;
        private final TextView messagetime;
        private final TextView messagecontent;
        private final ImageView messageimage;

        public XVH(@NonNull View itemView) {
            super(itemView);
            messagehead = itemView.findViewById(R.id.messagehead);
            messagename = itemView.findViewById(R.id.messagename);
            messagetime = itemView.findViewById(R.id.messagetime);
            messagecontent = itemView.findViewById(R.id.messagecontent);
            messageimage = itemView.findViewById(R.id.messageimage);
        }
    }
}
