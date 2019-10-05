package com.ciel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ciel.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserCrud  extends BaseMapper<User> {

    User getUserById(@Param("uid") String id);

    User getUserByNameAndPassword(@Param("u") User user);
}
