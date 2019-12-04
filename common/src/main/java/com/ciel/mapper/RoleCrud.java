package com.ciel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ciel.pojo.Role;
import com.ciel.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleCrud extends BaseMapper<Role> {


}
