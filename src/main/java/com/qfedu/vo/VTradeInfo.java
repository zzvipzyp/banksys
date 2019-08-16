package com.qfedu.vo;

import java.util.Date;

public class VTradeInfo {
    private Date createTime;
    private Double pay;
    private Double income;
    private Double balance;
    private String consumType;
    private String comment;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getPay() {
        return pay;
    }

    public void setPay(Double pay) {
        this.pay = pay;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getConsumType() {
        return consumType;
    }

    public void setConsumType(String consumType) {
        this.consumType = consumType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
