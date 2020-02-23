package com.ciel.provider.aop;

import org.springframework.aop.framework.AopContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
public class Tx {

//    <tx:method name="zhuan*" read-only="false" propagation="REQUIRED" isolation="DEFAULT" rollback-for="java.lang.Exception"/>
//        			<!-- read-only="false" ;是否只读事务; 会数据库优化提升性能; 查询方法使用此属性(true) -->
//        			<!-- propagation: 控制事务==传播行为==
//    REQUIRED:默认, 这@EnableTransactionManagement个方法如果被另一个方法调用,如果另一个方法有事务,就加入这个事务,没有就新建事务;
//    SUPPORTS: 如果另一个方法有事务,就加入这个事务,没有在非事务状态下执行; 直接调用也不会有事务;
//    MANDATORY: 必须在事务内部执行,没有事务报错
//    REQUIRES_NEW:如果另一个方法有事务,就挂起另一个方法的事务,自己新建一个单独事务(两个事务没有关系),没有就新建事务;
//    NOT_SUPPORTED:必须在非事务状态下执行; 如果另一个方法有事务就挂起;没有正常执行;
//    NEVER:必须在非事务状态下执行,如果另一个方法有事务就报错;没有正常执行;
//    NESTED:必须在事务状态下执行; 没有事务新建事务;有事务,就新建嵌套事务
//        			 -->
//
//        			 <!-- isolation: 控制事务隔离级别
//    脏读:读取了未提交数据;
//    不可重复读:针对某行数据进行update,一个事务两次读取数据不一致,(其他事务中途修改) ;锁住查询到的数据
//    幻读:两次事务的查询结果不一致,((其他事务添加或删除了符合条件的数据); 锁住整个表
//
//            DEFAULT ;数据默认;
//    READ_UNCOMMITTED; 可能会脏读;
//    READ_COMMITTED ;只能读取已提交数据;防止脏读,会出现不可重复读,幻读;
//    REPEATABLE_READ ;防止重复读,,读取的数据会添加锁, 防止脏读,不可重复读,会出现幻读;
//    SERIALIZABLE ;排队操作 ,锁住整个表
//        			  -->
//
//        			  <!-- rollback-for="java.lang.Exception" 出现什么异常时需要回滚
//    no-rollback-for="":出现什么异常时不回滚
//            timeout="-1" ; 超过该时间限制但事务还没有完成，则自动回滚事务
//        			   -->
}
