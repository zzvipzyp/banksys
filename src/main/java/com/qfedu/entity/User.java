package com.qfedu.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {
    private Integer uid;
    // 验证是否为空
    @NotEmpty(message = "卡号不能为空")
    @Pattern(regexp = "\\d+", message = "账号格式有误")
    private String bankCode;
    // 读取多语言配置文件中的内容
    @NotEmpty(message = "{password.empty}")
    @Size(min = 3,max = 10,message = "密码长度不符合要求")
    private String password;
    private Double balance;
    private Integer status;
    private String imgPath;

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
