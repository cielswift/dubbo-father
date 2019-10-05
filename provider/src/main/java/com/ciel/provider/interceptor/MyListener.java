package com.ciel.provider.interceptor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.err.println("应用启动");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.err.println("应用销毁");
    }


}
