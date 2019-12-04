package com.ciel.provider.controller;

import com.ciel.mapper.AppCrud;
import com.ciel.mapper.UserCrud;
import com.ciel.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected RedisTemplate redisTemplate;

    @Autowired
    protected AppCrud appCrud;

    @Autowired
    private UserCrud userCrud;

    @Autowired
    private UserService userService;

    @RequestMapping({"/", "/index"})
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping("/403")
    public ModelAndView unauthorizedRole() {
        System.out.println("------没有权限-------");
        return new ModelAndView("security/403");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView toLogin(Map<String, Object> map, HttpServletRequest request) {
        appCrud.selectList(null);
        request.getSession().invalidate();
        return new ModelAndView("security/login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(Map<String, Object> map, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        Subject subject = SecurityUtils.getSubject();

        mav.setViewName("index");

        if (!subject.isAuthenticated()) {

            UsernamePasswordToken upt = new UsernamePasswordToken(userName, password);

            upt.setRememberMe(true); //记住我

            try {
                subject.login(upt);
            } catch (AuthenticationException ae) { //所有认证异常的父类

                if(ae instanceof UnknownAccountException){
                    //用户不存在
                }

                if(ae instanceof IncorrectCredentialsException){
                    //密码错误
                }

                logger.error("登录失败");
                mav.setViewName("security/login");
            }
        }
        return mav;
    }

    @RequestMapping("/logout")
    public ModelAndView logOut(HttpSession session) {
        session.invalidate();
        return new ModelAndView("security/login");
    }

    @RequiresAuthentication  //表示当前Subject已经通过login 进行了身份验证；即Subject. isAuthenticated()返回true
    @RequestMapping("/auth1")
    public ModelAndView auth1(HttpServletRequest request){
        Map<String,String> map = new HashMap<>();
        map.put("name","xiapeixin1");


        Subject subject = SecurityUtils.getSubject();

        Session session = subject.getSession();
        HttpSession session1 = request.getSession();

        if(subject.hasRole("ADMIN")){}

        return new ModelAndView("redirect:/auth2");
        //return new ModelAndView("forward:/auth3");
    }

    @RequiresUser  //表示当前Subject已经身份验证或者通过记住我登录的
    @RequestMapping("/auth2")
    public Map auth2(){
        Map<String,String> map = new HashMap<>();
        map.put("name","xiapeixin2");
        return map;
    }

    @RequiresGuest //即是游客身份。
    @RequestMapping("/auth3")
    public Map auth3(){
        Map<String,String> map = new HashMap<>();
        map.put("name","xiapeixin3");
        return map;
    }

    @RequiresRoles(value={"ADMIN","USER"}, logical= Logical.OR) //表示当前Subject需要角色admin OR user
    @RequestMapping("/auth4")
    public Map auth4(){
        Map<String,String> map = new HashMap<>();
        map.put("name","xiapeixin4");
        return map;
    }

    @RequiresRoles(value={"TEACHER"}, logical= Logical.OR) //表示当前Subject需要角色admin OR user
    @RequestMapping("/auth4_1")
    public Map auth4_1(){
        Map<String,String> map = new HashMap<>();
        map.put("name","xiapeixin4_1");
        return map;
    }

    @RequiresPermissions (value={"user:a", "user:b"}, logical= Logical.AND)
    @RequestMapping("/auth5")
    public Map auth5(){
        Map<String,String> map = new HashMap<>();
        map.put("name","xiapeixin5");
        return map;
    }

    @RequiresPermissions (value={"user:c"}, logical= Logical.OR)
    @RequestMapping("/auth5_1")
    public Map auth5_1(){
        Map<String,String> map = new HashMap<>();
        map.put("name","xiapeixin5");
        return map;
    }



//    Shiro的认证注解处理是有内定的处理顺序的，如果有个多个注解的话，
//    前面的通过了会继续检查后面的，若不通过则直接返回，处理顺序依次为（与实际声明顺序无关）：

//    RequiresRoles
//     RequiresPermissions
//    RequiresAuthentication
//     RequiresUser
//    RequiresGuest

}
