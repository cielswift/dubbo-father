package com.ciel.provider.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect //当前是一个切面类
@Component
public class Aopc {
//<!-- 切入点表达式 :
//            *:任意返回值;
//    包名可以com.df.. (两个..)表示及其 子包;
//		      * *..*.save(..) 任意返回值,任意包下的任意类只要有save方法;  * *..*.*(..);所有方法都配置切面
//            (..)表示有无参数均可 ,也可以指定(java.lang.String,com.df.po.Boss)参数  ;
//		     (*)任意参数,但是必须有参数 -->
//
//		     <!-- 还有target表达式,this和target效果相同
//    target(com.df.aop.InterfaceOfMy); 实现InterfaceOfMy接口的类;类的所有的方法自动增加通知织入实现切面;
//    within(com.df.aop.*);within以包作为切入点;
//    args(java.lang.String,com.df.Book); 参数符合要求作为切入点
//		      -->


    @Pointcut("execution (public * com.ciel.provider.controller.AppController.*(..))")
    public void point(){}

    @Before("point()")  //其他类引用写全名
    public void before(JoinPoint joinPoint){
        //		System.out.println(Arrays.toString(joinPoint.getArgs()));; //返回被增强方法的参数列表
//		System.out.println(joinPoint.getSignature()); //获取连接点的方法签名对象；
//		System.out.println(joinPoint.getTarget().getClass());  //获得真实对象
//		System.out.println(joinPoint.getThis().getClass());  //获得代理对象

        System.out.println("bef");
    }

    @After("point()") //一定会执行
    public void after(JoinPoint joinPoint){
        System.out.println("after");
    }

    @AfterReturning(value = "point()",returning = "returnResult")
    public void AfterReturning(JoinPoint joinPoint,Object returnResult){ //joinPoint要放在前面

        System.out.println("AfterReturning");
    }

    @AfterThrowing(value = "point()",throwing = "exceptionResult")
    public void AfterThrowing(JoinPoint joinPoint,Exception exceptionResult){
        System.out.println("AfterThrowing");
    }

//    @Around("point()")
//    public void Around(ProceedingJoinPoint pjp) throws Throwable {
//        try {													//包含其他四个通知的执行时间点, 和其他冲突
//            System.out.println("环绕:方法之前执行");
//            pjp.proceed(); // 需要手动执行被增强的方法
//            System.out.println("环绕:方法之后执行");
//        }catch(Exception e) {
//            System.out.println("环绕:异常");
//        }finally {
//            System.out.println("环绕:最终");
//        }
//    }
}
