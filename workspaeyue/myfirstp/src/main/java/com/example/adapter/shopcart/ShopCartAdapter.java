package com.example.adapter.shopcart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.AddSubLayout;
import com.example.bean.shopcart.ShopCartgoods;
import com.example.myfirstp.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class ShopCartAdapter extends RecyclerView.Adapter<ShopCartAdapter.VH> {

    private Context context;
    private int number;
    public ShopCartAdapter(Context context) {
        this.context = context;
    }

    private ArrayList<ShopCartgoods> list = new ArrayList<>();
    //接口

    private Close close;

    public void setClose(Close close) {
        this.close = close;

    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LinearLayout.inflate(context, R.layout.shopcartlist_item, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VH vh, final int i) {
        final ShopCartgoods shopCartgoods = list.get(i);
        vh.shopcontent.setText(shopCartgoods.getCommodityName());
        vh.shopprice.setText("￥"+shopCartgoods.getPrice()+"");
        //选中
        vh.checkgoos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                shopCartgoods.setSelected(isChecked ? 1 : 0);
                sum();
            }
        });
        //如果商品为选中状态（1）则为true
        if (shopCartgoods.getSelected() == 1) {
            vh.checkgoos.setChecked(true);
        } else {
            vh.checkgoos.setChecked(false);
        }

        //设置图片
        vh.shopimage.setImageURI(list.get(i).getPic());
        vh.addsub.setCount(shopCartgoods.getCount());
        vh.addsub.setAddSubListener(new AddSubLayout.AddSubListener() {
            @Override
            public void addSub(int count) {
                shopCartgoods.setCount(count);
                sum();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(List<ShopCartgoods> result) {
        if (result != null)
        {
            list.addAll(result);
        }
    }


    public class VH extends RecyclerView.ViewHolder {

        private final CheckBox checkgoos;
        private final SimpleDraweeView shopimage;
        private final TextView shopcontent;
        private final TextView shopprice;
        private final AddSubLayout addsub;

        public VH(@NonNull View itemView) {
            super(itemView);
            checkgoos = itemView.findViewById(R.id.check_shop_goods);
            shopimage = itemView.findViewById(R.id.image_shop);
            shopcontent = itemView.findViewById(R.id.text_shop_content);
            shopprice = itemView.findViewById(R.id.text_shop_price);
            addsub = itemView.findViewById(R.id.addsub);
        }
    }


    //全部选中或者全部取消
    public void checkAll(boolean isCheck) {
        for (int i = 0; i < list.size(); i++) {
            ShopCartgoods shoppingBean = list.get(i);
            shoppingBean.setIscheck(isCheck);
            //1代表为全部选中
            shoppingBean.setSelected(isCheck ? 1 : 0);

        }
        //刷新适配器
        notifyDataSetChanged();
        //选中之后计算总价方法
        sum();
    }

    public List<ShopCartgoods> close() {
        list.clear();
        for (int i = 0; i < list.size(); i++) {
            ShopCartgoods shopCartgoods = list.get(i);
            if (shopCartgoods.isIscheck()) {
                list.add(shopCartgoods);
            }
        }
        return list;
    }

    //计算总价格
    private void sum() {
        double totalPrice = 0;
        //循环商品
        for (int i = 0; i < list.size(); i++) {
            ShopCartgoods shopCartgoods = list.get(i);
            //如果是选中状态才能获取价格（1,是选中状态,0是未选中状态）
            if (shopCartgoods.getSelected() == 1) {
                //价钱乘以数量得到总价格
                //叠加
                totalPrice = totalPrice + shopCartgoods.getPrice() * shopCartgoods.getCount();
            }

        }
        //给总价格接口设置值
        if (totalPriceListener != null) {
            totalPriceListener.totalPrice(totalPrice);
        }
    }
    private TotalPriceListener totalPriceListener;
    public void setTotalPriceListener(TotalPriceListener totalPriceListener) {
        this.totalPriceListener = totalPriceListener;
    }
    //内部类接口5
    public interface TotalPriceListener {
        void totalPrice(double totalPrice);
    }

    public interface Close {
        void close(ShopCartgoods shopCartgoods);
    }
}
