package com.ciel.provider.j8.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;

public class CglibProxyFactory implements MethodInterceptor {
    private Object target;

    public CglibProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        // 创建 Enhancer 对象
        Enhancer enhancer = new Enhancer();
        // 设置目标对象的Class
        enhancer.setSuperclass(target.getClass());
        // 设置回调操作，相当于InvocationHandler
        enhancer.setCallback(this);

        return enhancer.create();
    }
    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy)
            throws Throwable {
        System.out.println("开始事务...");
        // 两种方式执行方法，第二行注释掉的，和当前行代码效果相同，下面会分析；
        Object invoke = method.invoke(target, args);
        // Object invoke = methodProxy.invokeSuper(proxy, args);
        System.out.println("提交/回滚事务...");
        return invoke;
    }

    public static void main(String[] args) throws IOException {
        Programmer programmer = new Programmer();

        CglibProxyFactory cglibProxyFactory = new CglibProxyFactory(programmer);

        Programmer proxyInstance = (Programmer)cglibProxyFactory.getProxyInstance();

        proxyInstance.work("aa");

        //类资源加载器
        URL resource = proxyInstance.getClass().getResource("./"); //类所在路径,可以使用 ../../切换
        URL resource1 = proxyInstance.getClass().getResource("/"); //classpath目录下
        System.out.println(resource);
        System.out.println(resource1);

        InputStream resourceAsStream = proxyInstance.getClass().getResourceAsStream("./Programmer.class");

        byte[] temp = new byte[1024*1024];
        resourceAsStream.read(temp);

        System.out.println(new String(temp));

        URL resource2 = proxyInstance.getClass().getClassLoader().getResource("./");
        //在使用 ClassLoader().getResource 获取路径时，不能以 "/" 开头，且路径总是从 classpath 根路径开始；
        System.out.println(resource2);

        //获取类路径下文件
        File cfgFile = ResourceUtils.getFile("classpath:public/问题填报模板.xlsx");

        //获取配置文件的配置信息;
        //SpringContextUtils.getAppConfig("mybatisplus.redis.prefix",

        /**
         * 获取方法参数名称
         */
        Method[] methods = CglibProxyFactory.class.getMethods();
        for (Method me: methods){
            String[] parameterNames = new LocalVariableTableParameterNameDiscoverer().getParameterNames(me);


            System.out.println("METHOD_NAME:"+me.getName());
            Arrays.stream(parameterNames).forEach(System.out::print);
            System.out.println();

        }
    }
}
