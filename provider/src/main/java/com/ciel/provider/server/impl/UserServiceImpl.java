package com.ciel.provider.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ciel.mapper.UserCrud;
import com.ciel.pojo.User;
import com.ciel.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserCrud, User> implements UserService {
    @Override
    public User Login(User user) {

        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.likeRight(User::getName, "香蕉"); //lambda 写法的条件;

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

    @Override
    public User findByUserName(String userName) {
        Map<String,Object> map = new HashMap<>();
        map.put("name",userName);
        List<User> users = baseMapper.selectByMap(map);
        return users.isEmpty()?null:users.get(0);
    }

    @Override
    public User findByUserNameAndPassword(String userName,String password) {
        Map<String,Object> map = new HashMap<>();
        map.put("name",userName);
        map.put("password",password);
        List<User> users = baseMapper.selectByMap(map);
        return users.isEmpty()?null:users.get(0);
    }

    @Override
    public User findById(String id) {
        return baseMapper.getUserById(id);
    }
}
