package com.example.bean.shopcart;

public class JoinShopbean {
    private int commodityId;
    private int count;

    public JoinShopbean(int commodityId, int count) {
        this.commodityId = commodityId;
        this.count = count;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
