package com.ciel.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ciel.cache.RedisMybatisCache;
import com.ciel.pojo.App;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.google.common.base.Joiner;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

@Mapper
@CacheNamespace(implementation = RedisMybatisCache.class, eviction = RedisMybatisCache.class)
public interface AppCrud extends BaseMapper<App> {

    Page<App> myPage(Page<App> page, @Param(Constants.WRAPPER) QueryWrapper<App> appQueryWrapper);

    void myChange(@Param(Constants.WRAPPER)UpdateWrapper<App> updateWrapper);

    App selectByName(String xia);

    List<App> appsByUserId(@Param("uid") String uid);


    @SelectProvider(type=AppCrud.SQLProvider.class, method = "getTrafficLightStart")
    List<Map<String,Object>> getTrafficLightStart(@Param("vin") List<String> vinList);

    class SQLProvider{
        public String getTrafficLightStart(@Param("vin") List<String> vinList){
            return new SQL(){
                {
                    SELECT("  *  ");
                    FROM("test").WHERE(String.format("vin in ('%s')", Joiner.on("','").skipNulls().join(vinList)));
                }
            }.toString();

        }

    }

}
