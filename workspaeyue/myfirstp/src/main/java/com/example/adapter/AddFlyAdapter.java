package com.example.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myfirstp.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;


public class AddFlyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public AddFlyAdapter(List<Object> mDatas, Context mContext, Open open) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        this.open = open;
    }

    private List<Object> mDatas;
    private Context mContext;
    Open open;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View inflate = LayoutInflater.from(mContext).inflate(R.layout.addfly_item, viewGroup, false);
        return new Vh_addFly(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {

        if (viewHolder instanceof Vh_addFly){
            if(mDatas.get(position) instanceof String ){
                String imageUrl = (String) mDatas.get(position);
                if (imageUrl.contains("http:")) {//加载http
                    ((Vh_addFly) viewHolder).circle_image.setImageURI(imageUrl);
                } else {//加载sd卡
                    Uri uri = Uri.parse("file://" + imageUrl);
                    ((Vh_addFly) viewHolder).circle_image.setImageURI(uri);
                }
            }else {
                int id = (int) mDatas.get(position);
                Uri uri = Uri.parse("res:///" + id);
                ((Vh_addFly) viewHolder).circle_image.setImageURI(uri);
            }
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position==0){
                    open.onDakaiXiangCe();
                }else {
                    Toast.makeText(mContext, ""+position, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class Vh_addFly extends RecyclerView.ViewHolder {

        private final SimpleDraweeView circle_image;

        public Vh_addFly(@NonNull View itemView) {
            super(itemView);
            circle_image = itemView.findViewById(R.id.circle_image);
        }
    }
    public interface Open{
        void onDakaiXiangCe();
    }
}
