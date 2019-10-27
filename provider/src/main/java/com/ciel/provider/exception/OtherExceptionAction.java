package com.ciel.provider.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice //全局异常处理
public class OtherExceptionAction {  //针对某些异常进程处理

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

}
