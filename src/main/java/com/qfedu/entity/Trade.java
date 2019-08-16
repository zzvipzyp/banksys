package com.qfedu.entity;

import java.util.Date;

public class Trade {
    private Integer id;
    private Integer uid;
    private Integer otherUid;
    private Double money;
    private String consumType;
    private Date createTime;
    private String comment;
    private Double balance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getOtherUid() {
        return otherUid;
    }

    public void setOtherUid(Integer otherUid) {
        this.otherUid = otherUid;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }


    public String getConsumType() {
        return consumType;
    }

    public void setConsumType(String consumType) {
        this.consumType = consumType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
