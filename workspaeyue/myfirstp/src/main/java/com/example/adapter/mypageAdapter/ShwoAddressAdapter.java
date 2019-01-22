package com.example.adapter.mypageAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bean.mypage.Addaddressbean;
import com.example.myfirstp.R;

import java.util.ArrayList;
import java.util.List;

public class ShwoAddressAdapter extends RecyclerView.Adapter<ShwoAddressAdapter.VH> {

    private Context context;

    public ShwoAddressAdapter(Context context) {
        this.context = context;
    }

    private ArrayList<Addaddressbean> list = new ArrayList<>();


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LinearLayout.inflate(context, R.layout.showaddress_list, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.name.setText(list.get(i).getRealName());
        vh.phone.setText(list.get(i).getPhone());
        vh.content.setText(list.get(i).getAddress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void additem(List<Addaddressbean> result) {
        if (result != null)
        {
            list.addAll(result);
        }
    }

    public class VH extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView phone;
        private final TextView content;

        public VH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.show_name);
            phone = itemView.findViewById(R.id.show_phone);
            content = itemView.findViewById(R.id.show_content);
        }
    }
}
