package com.example.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bean.CircleBean;
import com.example.http.SpacingItemDecoration;
import com.example.myfirstp.R;
import com.example.util.StringUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.CVH> {

    private Context context;
    private ArrayList<CircleBean> list = new ArrayList<>();
    public CircleAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.criclerecyclervew_list, viewGroup, false);
        return new CVH(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final CVH cvh, final int i) {
        CircleBean circleBean = list.get(i);
        cvh.circlehead.setImageURI(circleBean.getHeadPic());
        cvh.circlename.setText(circleBean.getNickName());//昵称
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
        Date date = new Date(circleBean.getCreateTime());        //获取当前时间
        cvh.circletime.setText(simpleDateFormat.format(date));//时间
        cvh.circlecontent.setText(circleBean.getContent());//内容
        cvh.circletext.setText(circleBean.getGreatNum()+"");
        if (StringUtils.isEmpty(circleBean.getImage()))
        {
            cvh.gridView.setVisibility(View.GONE);
        }else {
            cvh.gridView.setVisibility(View.VISIBLE);
            String[] split = circleBean.getImage().split(",");
            int imageCount = split.length;

            int colNum;//列数
            if (imageCount == 1){
                colNum = 1;
            }else if (imageCount == 2||imageCount == 4){
                colNum = 2;
            }else {
                colNum = 3;
            }

            cvh.imageAdapter.clear();//清空
          //for (int j = 0; j <imageCount ; j++) {
                cvh.imageAdapter.addAll(Arrays.asList(split));
           //}

            cvh.gridLayoutManager.setSpanCount(colNum);

            cvh.imageAdapter.notifyDataSetChanged();
        }

        //点赞
        cvh.circledianzan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                    isCheckLin.onclick(isChecked, list.get(i).getId(),i);
                    cvh.circletext.setText(String.valueOf(list.get(i).getGreatNum()+1));

                }else {

                    isCheckLin.onclick(isChecked, list.get(i).getId(),i);
                    cvh.circletext.setText(String.valueOf(list.get(i).getGreatNum()));

                }
            }
        });
    }

    //回调接口
    public void setIsCheckLin(IsCheckLin isCheckLin) {
        this.isCheckLin = isCheckLin;
    }

    private IsCheckLin isCheckLin;
    public interface IsCheckLin{
        void onclick(boolean ischeck,int pid,int position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    public void adddata(List<CircleBean> data) {
        if (data != null)
        {
            list.addAll(data);
        }
    }


    public class CVH extends RecyclerView.ViewHolder {

        private final SimpleDraweeView circlehead;
        private final TextView circlename;
        private final TextView circletime;
        private final TextView circlecontent;
        private final RecyclerView gridView;
        private final GridLayoutManager gridLayoutManager;
        private final ImageAdapter imageAdapter;
        private final TextView circletext;
        private final CheckBox circledianzan;

        public CVH(@NonNull View itemView) {
            super(itemView);
            circlehead = itemView.findViewById(R.id.circlehead);
            circlename = itemView.findViewById(R.id.circlename);
            circletime = itemView.findViewById(R.id.circletime);
            circlecontent = itemView.findViewById(R.id.circlecontent);
            circletext = itemView.findViewById(R.id.circle_text);
            circledianzan = itemView.findViewById(R.id.circledianzan);
            gridView = itemView.findViewById(R.id.grid_view);
            imageAdapter = new ImageAdapter();
            int space = context.getResources().getDimensionPixelSize(R.dimen.dip_10);//图片间距
            gridLayoutManager = new GridLayoutManager(context,3);
            gridView.addItemDecoration(new SpacingItemDecoration(space));
            gridView.setLayoutManager(gridLayoutManager);
            gridView.setAdapter(imageAdapter);
        }
    }
}
