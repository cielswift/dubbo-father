package com.ciel.provider.server.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ciel.mapper.UserCrud;
import com.ciel.pojo.User;
import com.ciel.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserCrud, User> implements UserService {
    @Override
    public User Login(User user) {
        return baseMapper.getUserByNameAndPassword(user);
    }

    @Override
    public Boolean zhuanZhang(String f, String r) {
        User user = baseMapper.selectById(f);
        if(user.getPrice().compareTo(new BigDecimal("200")) == -1){
            throw new RuntimeException("yue余额不足");
        }

        user.setPrice(user.getPrice().subtract(new BigDecimal("200")));
        baseMapper.updateById(user);

        if (System.currentTimeMillis() % 2 != 0) {
            throw new RuntimeException("随机异常");
        }

        User user1 = baseMapper.selectById(r);
        user1.setPrice(user1.getPrice().add(new BigDecimal("200")));
        baseMapper.updateById(user1);

        return true;
    }
}
