package com.ciel.provider.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ciel.mapper.UserCrud;
import com.ciel.pojo.User;
import com.ciel.service.UserService;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserCrud, User> implements UserService {


    /**
     * 手动提交事务
     */
//    @Override
//    @Transactional(rollbackFor = AlertException.class)
//    public boolean clearPower(String userId) throws AlertException {
//

//
//        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
//
//        transactionTemplate.execute(new TransactionCallback<Integer>() {
//        @Override
//        public Integer doInTransaction(TransactionStatus status) {
//            try {
//                sysUserRoleService.remove(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, userId));
//                sysUserPermissionService.remove(new LambdaQueryWrapper<SysUserPermission>().eq(SysUserPermission::getUserId, userId));
//
//                if(System.currentTimeMillis() % 2 ==0){
//                    throw new AlertException("清除权限失败");
//                }
//
//            }catch (AlertException e){
//                status.setRollbackOnly();
//            }
//
//            return 1;
//        }
//    });




//        DataSourceTransactionManager transactionManager = applicationContext.getBean(
//                "transactionManager", DataSourceTransactionManager.class);//这里是spring手动注入bean,也可以使用自动注入 HqznContext是一个工具类:主要用于简化spring手动注入代码
//        //2.获取事务定义
//        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//        //3.设置事务隔离级别，开启新事务
//        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
//        //4.获得事务状态
//        TransactionStatus status = transactionManager.getTransaction(def);
//
//        try {
//            sysUserRoleService.remove(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, userId));
//            sysUserPermissionService.remove(new LambdaQueryWrapper<SysUserPermission>().eq(SysUserPermission::getUserId, userId));
//
//            transactionManager.commit(status);
//        } catch (Exception e) {
//            transactionManager.rollback(status);
//            throw new AlertException("清除权限失败");
//        }
//        return true;
//    }

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
