package com.ciel.provider.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InterceptorAnna extends HandlerInterceptorAdapter {

    //多个拦截器执行顺序 ; 先执行1号preHandle,2号拦截器的preHandle, 目标方法,2号postHandle,1号postHandle,
    //2号afterCompletion,1号afterCompletion;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("拦截了异步执行的方法 + /app/asyn");
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
