package com.example.bean.mypage;

import java.util.List;

public class WalletBean {
    private int balance;
    private List<?> detailList;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<?> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<?> detailList) {
        this.detailList = detailList;
    }
}
