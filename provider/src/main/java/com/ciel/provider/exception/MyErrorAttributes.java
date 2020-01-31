package com.ciel.provider.exception;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.DefaultErrorViewResolver;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Properties;


@Component
public class MyErrorAttributes  extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        if ((Integer)map.get("status") == 500) {
            map.put("message", "服务器内部错误!");
        }
        return map;
    }
}

//@Component //错误页面
//class MyErrorViewResolver extends DefaultErrorViewResolver {
//    public MyErrorViewResolver(ApplicationContext applicationContext, ResourceProperties resourceProperties) {
//        super(applicationContext, resourceProperties);
//    }
//    @Override
//    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
//        return new ModelAndView("/aaa/123", model);
//    }
//}


//@Configuration
//public class GlobalException {
//
//	/**
//	 * 该方法必须要有返回值。返回值类型必须是： SimpleMappingExceptionResolver
//	 */
//	@Bean
//	public SimpleMappingExceptionResolver getSimpleMappingExceptionResolver() {
//		SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
//		Properties mappings = new Properties();
//		/**
//		 * 参数一：异常的类型，注意必须是异常类型的全名 参数二：视图名称
//		 */
//		mappings.put("java.lang.ArithmeticException", "error1");
//		mappings.put("java.lang.NullPointerException", "error2");
//		// 设置异常与视图信息的映射
//		resolver.setExceptionMappings(mappings);
//		return resolver;
//	}
//}
