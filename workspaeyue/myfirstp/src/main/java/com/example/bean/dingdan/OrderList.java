package com.example.bean.dingdan;

import java.util.List;

public class OrderList<T> {
//   "expressCompName": "京东快递",
//           "expressSn": "1001",
//           "orderId": "20190111201028137731",
//           "orderStatus": 1,
//           "payAmount": 278,
//           "payMethod": 1,
//           "userId": 731
    //        detailList
    private String expressCompName;
    private String expressSn;
    private String orderId;
    private int  orderStatus;
    private int   payAmount;
    private int payMethod;
    private int userId;
    private T detailList;

    public String getExpressCompName() {
        return expressCompName;
    }

    public void setExpressCompName(String expressCompName) {
        this.expressCompName = expressCompName;
    }

    public String getExpressSn() {
        return expressSn;
    }

    public void setExpressSn(String expressSn) {
        this.expressSn = expressSn;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(int payAmount) {
        this.payAmount = payAmount;
    }

    public int getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(int payMethod) {
        this.payMethod = payMethod;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public T getDetailList() {
        return detailList;
    }

    public void setDetailList(T detailList) {
        this.detailList = detailList;
    }
}
