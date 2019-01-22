package com.example.bean;

public class RxxpBean<T> {
    T commodityList;
    private int id;
    private String name;

    public T getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(T commodityList) {
        this.commodityList = commodityList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
