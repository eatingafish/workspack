package com.example.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Judge {
    @Id(autoincrement = true)
    private long id;
    private String phone;
    private String passward;
    private boolean ischecked;
    @Generated(hash = 995091487)
    public Judge(long id, String phone, String passward, boolean ischecked) {
        this.id = id;
        this.phone = phone;
        this.passward = passward;
        this.ischecked = ischecked;
    }
    @Generated(hash = 1458191321)
    public Judge() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPassward() {
        return this.passward;
    }
    public void setPassward(String passward) {
        this.passward = passward;
    }
    public boolean getIschecked() {
        return this.ischecked;
    }
    public void setIschecked(boolean ischecked) {
        this.ischecked = ischecked;
    }



}
