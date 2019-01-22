package com.example.bean.shopcart;

import java.util.List;

public class ShopCartgoods {

    /**
     * commodityId : 29
     * commodityName : 秋冬新款平底毛毛豆豆鞋加绒单鞋一脚蹬懒人鞋休闲
     * count : 1
     * pic : http://172.17.8.100/images/small/commodity/nx/ddx/5/1.jpg
     * price : 278
     */

    private int commodityId;
    private String commodityName;
    private int count;
    private String pic;
    private int price;
    private int selected;
    private boolean ischeck;

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public boolean isIscheck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
