package com.qfedu.service;

import com.qfedu.entity.User;

public interface UserService {
    public User login(String code, String password);

    public User selectByCode(String bankCode);

    public Double selectBalanceByCode(String code);

    public void updateHeadImg(Integer uid, String imgPath );
}
