package com.example.bean.mypage;

public class WalletJia {
    private String money;

    public WalletJia(String money, String time) {
        this.money = money;
        this.time = time;
    }

    private String time;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
