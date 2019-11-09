package com.ciel.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ciel.pojo.App;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AppCrud extends BaseMapper<App> {

    Page<App> myPage(Page<App> page, @Param(Constants.WRAPPER) QueryWrapper<App> appQueryWrapper);

    void myChange(@Param(Constants.WRAPPER)UpdateWrapper<App> updateWrapper);

    App selectByName(String xia);

    List<App> appsByUserId(@Param("uid") String uid);

    
}
