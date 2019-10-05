package com.ciel.provider.controller;

import com.ciel.pojo.User;
import com.ciel.service.AppServicer;
import com.ciel.service.UserService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/security")
@SessionAttributes("user")
public class AppSecurity {

    @Autowired
    private UserService userService;

    @Value("${fileUpload.img.user}")
    private String imgUserPath;

    @RequestMapping("/s1")
    public Map s1(){
        Map<String,String> map = new HashMap<>();
        map.put("xia","xiapeixin-s1");
        return map;
    }
    @RequestMapping("/s2")
    public Map s2(){
        Map<String,String> map = new HashMap<>();
        map.put("xia","xiapeixin-s2");
        return map;
    }

    @RequestMapping("/s3")
    public Map s3(){
        Map<String,String> map = new HashMap<>();
        map.put("xia","xiapeixin-s3");
        return map;
    }

    @RequestMapping("/log")
    public ModelAndView log(){
        return new ModelAndView("security/login.html");
    }

    @RequestMapping("/login")
    public ModelAndView login(User u, MultipartFile headSculpture){
//        ModelAndView ma = new ModelAndView();
//
//        String fileName = headSculpture.getOriginalFilename();
//
//        String fn = UUID.randomUUID().toString().replace("-","")+".jpg";
//        DateTime dt = new DateTime(new Date());
//        String ph = dt.getYear()+"/"+dt.getMonthOfYear()+"/"+dt.getDayOfMonth();
//        File filePath = new File(imgUserPath+ph);
//
//        if(!filePath.exists()){
//            filePath.mkdirs();
//        }
//
//        File dest = new File(filePath,fn);
//        try {
//            headSculpture.transferTo(dest);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
////        String md5Password = DigestUtils.md5DigestAsHex(u.getPassword().getBytes());
////        u.setPassword(md5Password);
//
//        User login = userService.Login(u);
//        if(login!=null) {
//            ma.addObject("user", login);
//            ma.setViewName("security/securityXia.html");
//
//        }else {
//            ma.addObject("errMsg","用户或密码错误");
//            ma.setViewName("security/login.html");
//        }
//
//        return ma;

        return new ModelAndView("security/securityXia.html");
    }

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("security/securityXia.html");
    }


}
