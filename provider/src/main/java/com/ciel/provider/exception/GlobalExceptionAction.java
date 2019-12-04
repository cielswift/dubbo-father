package com.ciel.provider.exception;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration  //全局异常处理
public class GlobalExceptionAction implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("err","系统发生了异常");
        mav.setViewName("/fram/error");
        return mav;
        //如果希望返回跳转页面，则需要实现HandlerExceptionResolver类来进行异常处理并跳转。
    }

}
