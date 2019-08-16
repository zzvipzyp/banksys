package com.qfedu.service.impl;

import com.github.pagehelper.PageHelper;
import com.qfedu.dao.TradeDao;
import com.qfedu.dao.UserDao;
import com.qfedu.entity.Trade;
import com.qfedu.entity.User;
import com.qfedu.service.TradeService;
import com.qfedu.vo.VTradeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TradeServiceImpl implements TradeService {
    @Autowired(required = false)
    private TradeDao tradeDao;

    @Autowired(required = false)
    private UserDao userDao;
    @Override
    public List<VTradeInfo> findAllTrades(Integer uid, Date beginTime, Date endTime, Integer page, Integer limit) {

        // 设置页码和每页显示的记录数，该语句后面，紧跟着数据库查询相关的语句
        PageHelper.startPage(page, limit);
        List<VTradeInfo> list = tradeDao.findAll(uid, beginTime, endTime);

        // 获取总记录数
        //((Page)list).getTotal();

        return list;
    }

    @Override
    public void transfer(String code, String otherCode, Double money) {
        User loginUser = userDao.findByCode(code);
        User otherUser = userDao.findByCode(otherCode);
        if(otherUser == null){
            throw new RuntimeException("用户不存在");
        }
        if(loginUser.getBankCode().equals(otherCode)){
            throw new RuntimeException("不能给自己转账");
        }

        if(loginUser.getBalance() < money){
            throw new RuntimeException("余额不足");
        }

        // 当前账户减钱
        Trade outTrade = new Trade();
        outTrade.setUid(loginUser.getUid());
        outTrade.setBalance(loginUser.getBalance() - money);
        outTrade.setMoney(0 - money);
        tradeDao.add(outTrade);

        loginUser.setBalance(loginUser.getBalance() - money);
        userDao.update(loginUser);

        // 另一账户加钱
        Trade inTrade = new Trade();
        inTrade.setUid(otherUser.getUid());
        inTrade.setBalance(otherUser.getBalance() + money);
        inTrade.setMoney(money);
        tradeDao.add(inTrade);

        otherUser.setBalance(otherUser.getBalance() + money);
        userDao.update(otherUser);

    }
}
