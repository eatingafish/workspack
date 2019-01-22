package com.example.bean;

import java.util.List;

public class CircleBean {

    /**
     * commodityId : 1
     * content : 哒哒哒哒哒哒哒哒
     * createTime : 1546556451000
     * greatNum : 36
     * headPic : http://172.17.8.100/images/small/head_pic/2019-01-03/20190103184923.jpg
     * id : 239
     * image : http://172.17.8.100/images/small/circle_pic/2019-01-03/6827420190103170051.jfif
     * nickName : 五块钱
     * userId : 70
     * whetherGreat : 2
     */

    private int commodityId;
    private String content;
    private long createTime;
    private int greatNum;
    private String headPic;
    private int id;
    private String image;
    private String nickName;
    private int userId;
    private int whetherGreat;

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getGreatNum() {
        return greatNum;
    }

    public void setGreatNum(int greatNum) {
        this.greatNum = greatNum;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWhetherGreat() {
        return whetherGreat;
    }

    public void setWhetherGreat(int whetherGreat) {
        this.whetherGreat = whetherGreat;
    }
}
