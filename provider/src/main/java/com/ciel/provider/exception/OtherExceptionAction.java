package com.ciel.provider.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class OtherExceptionAction { //全局异常处理 //针对某些异常进程处理

    @ExceptionHandler({RuntimeException.class})
    public ModelAndView mav2(Exception e, HttpServletRequest request) {
        ModelAndView ma = new ModelAndView();
        ma.addObject("err", e.toString());
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


    //我们可以将一些公共的数据定义在添加了 @ControllerAdvice 注解的类中，这样，在每一个 Controller 的接口中，就都能够访问导致这些数据

    @ModelAttribute(name = "md")
    public Map<String,Object> mydata() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("age", 99);
        map.put("gender", "男");
        return map;
    }


}
