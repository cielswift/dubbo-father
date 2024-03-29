package com.ciel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ciel.pojo.User;

public interface UserService extends IService<User> {
    User Login(User user);

    public Boolean zhuanZhang(String f,String r);

    User findByUserName(String userName);

    User findById(String id);

    User findByUserNameAndPassword(String userName,String password);
}
