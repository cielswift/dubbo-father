package com.ciel.provider.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
//        1全局异常处理 2全局数据绑定 3全局数据预处理
public class OtherExceptionAction { //全局异常处理 //针对某些异常进程处理

    @ExceptionHandler({UnauthorizedException.class})
    public ModelAndView mav2(Exception e, HttpServletRequest request) {
        ModelAndView ma = new ModelAndView();
        ma.addObject("err", "权限不足 异常");
        ma.setViewName("/fram/error");
        return ma;
    }

    @ExceptionHandler({NullPointerException.class})
    public ModelAndView mav(Exception e,HttpServletRequest request) {
        ModelAndView ma = new ModelAndView();
        ma.addObject("err", e.toString());
        ma.setViewName("error");
        return ma;
    }


    //可以将一些公共的数据定义在添加了 @ControllerAdvice 注解的类中，这样，在每一个 Controller 的接口中，就都能够访问导致这些数据
    @ModelAttribute(name = "md")
    public Map<String,Object> mydata() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("age", 99);
        map.put("gender", "男");
        return map;
    }


   // @InitBinder(“b”) 注解表示该方法用来处理和Book和相关的参数,在方法中,给参数添加一个 b 前缀,即请求参数要有b前缀.
    @InitBinder("bb")
    public void b(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("bb.");
    }
    @InitBinder("aa")
    public void a(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("aa.");
    }

}
