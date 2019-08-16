package com.qfedu.service.impl;

import com.qfedu.dao.UserDao;
import com.qfedu.entity.User;
import com.qfedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserDao userDao;
    @Override
    public User login(String code, String password) {
        User user = userDao.findByCode(code);
        if(user == null){
            throw new RuntimeException("账号错误");
        }
        if(!user.getPassword().equals(password)){
            throw new RuntimeException("密码错误");
        }
        return user;

    }

    @Override
    public Double selectBalanceByCode(String code) {
        return userDao.selectBalance(code);
    }

    @Override
    public void updateHeadImg(Integer uid, String imgPath) {
        User u = new User();
        u.setUid(uid);
        u.setImgPath(imgPath);
        userDao.update(u);
    }

    @Override
    public User selectByCode(String bankCode) {
        return userDao.findByCode(bankCode);
    }
}
