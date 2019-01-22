package com.example.bean.showbean;

public class ChanceBean<T> {

    private int id;
    private String name;
    private T commodityList;

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

    public T getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(T commodityList) {
        this.commodityList = commodityList;
    }
}
