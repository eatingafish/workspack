package com.example.bean;

public class Goodsbean <T>{
    T list;
    private String sellerName;
    private String sellerid;

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }
}
