package com.ciel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ciel.pojo.Permissions;
import com.ciel.pojo.Role;
import com.ciel.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserCrud  extends BaseMapper<User> {

    User getUserById(@Param("uid") String id);

    User getUserByNameAndPassword(@Param("u") User user);

    List<Role> getRolesByUserId(@Param("uid") String id);

    List<Permissions> getPermissionsByUserId(@Param("uid") String id);
}
