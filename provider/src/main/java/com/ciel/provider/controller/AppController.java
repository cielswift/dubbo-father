package com.ciel.provider.controller;

import com.ciel.pojo.App;
import com.ciel.service.AppServicer;
import com.ciel.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringEscapeUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    @Qualifier("appServerImpl")
    private AppServicer appServicer;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    Logger logger = LoggerFactory.getLogger(getClass());

    public void run() {
        logger.trace("ss");
        logger.debug("999");
        logger.warn("ss");
    }

    @RequestMapping("/asyn") //异步处理
    public Callable<String> asyn() {
        String name = Thread.currentThread().getName();

        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                String name = Thread.currentThread().getName();
                return new ObjectMapper().writeValueAsString(appServicer.list());
            }
        };
    }

    @RequestMapping("/asyn2")
    public DeferredResult<String> asyn2() {
        DeferredResult<String> deferredResult = new DeferredResult<>(3000L, "defeat"); //超过多次时间没有相应

        rabbitTemplate.convertAndSend("peitian","",deferredResult);

        deferredResult.setResult("make love"); //一般在其他地方设置值,来响应
        return deferredResult;
    }

    @RequestMapping("/tx")
    public ModelAndView tx() {
        userService.zhuanZhang("4028aa476c71b652016c71b849ca0001", "4028aa411111b652016c71b849ca0001");

        ModelAndView model = new ModelAndView();
        model.addObject("xia", "xiapeixin");
        model.setViewName("fram/index");
        return model;
    }

    @RequestMapping("/list")
    public List<App> list(@DateTimeFormat(pattern = "yyyy-MM-hh HH:mm:ss") Date date) {
        return appServicer.list();
    }

    @GetMapping(value = {"/cache", "/ca"})
    public List<App> cache(String name) {
        return appServicer.myApp(name);
    }

    @GetMapping(value = {"/change"})
    public List<App> change(App app) {
        return appServicer.chang(app);
    }

    @GetMapping(value = {"/reomve"})
    public int reomve(App app) {
        return appServicer.remove("app");
    }

//    th:include：加载模板的内容： 读取加载节点的内容（不含节点名称），替换div内容
//    th:replace：替换当前标签为模板中的标签，加载的节点会整个替换掉加载他的div

//    <!-- 加载模板的内容： 读取加载节点的内容（不含节点名称），替换<div>的内容 -->
//<div> the public pagination</div>
//<!-- 替换当前标签为模板中的标签： 加载的节点会整个替换掉加载他的<div>  -->
//<span> the public pagination</span>

    //    <!--  语法说明  "::"前面是模板文件名，后面是选择器 -->
//    <div th:include="template/footer::copy"></div>
//    <!-- 只写选择器，这里指fragment名称，则加载本页面对应的fragment -->
//    <div th:include="::#thispage"></div>
//    <!-- 只写模板文件名，则加载整个页面 -->
//    <div th:include="template/footer"></div>


    @GetMapping("/index")
    public ModelAndView index() {

        if (System.currentTimeMillis() % 2 != 0) {
            throw new RuntimeException("随机异常");
        }

        ModelAndView model = new ModelAndView();
        model.addObject("xia", "xiapeixin");
        model.setViewName("fram/index");
        return model;
    }

    @PutMapping("/app/{name}/{userId}/{deleted}")
    public ModelAndView putTest(App app) {


        ModelAndView model = new ModelAndView();
        model.addObject("xia", "xiapeixin");
        model.setViewName("fram/index");
        return model;
    }

    @DeleteMapping("/app/{id}")
    public ModelAndView deleted(App app) {
        ModelAndView model = new ModelAndView();
        model.addObject("xia", "xiapeixin");
        model.setViewName("fram/index");
        return model;
    }

    @Value("${fileUpload.img.user}")
    private String imgUserPath;

    @Value("${fileRequest.img.user}")
    private String imgUserRequest;

    @PostMapping(value = "/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException, JSONException {
        String fileName = file.getOriginalFilename();

        String fn = UUID.randomUUID().toString().replace("-", "") + ".jpg";
        DateTime dt = new DateTime(new Date());
        String ph = dt.getYear() + "/" + dt.getMonthOfYear() + "/" + dt.getDayOfMonth() + "/";
        File filePath = new File(imgUserPath + ph);

        if (!filePath.exists()) {
            filePath.mkdirs();
        }

        File dest = new File(filePath, fn);
        file.transferTo(dest);

        JSONObject jsonpObject = new JSONObject();
        jsonpObject.put("errno", 0);

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(imgUserRequest + ph + fn);

        jsonpObject.put("data", jsonArray);

        String s = StringEscapeUtils.unescapeJava(jsonpObject.toString());
        return s;
    }

    @PostMapping(value = "/text")
    public ModelAndView text(@RequestParam("text") String text) {
        String s = HtmlUtils.htmlEscape(text);
        String s1 = HtmlUtils.htmlEscapeHex(text);
        String s2 = HtmlUtils.htmlUnescape(text);

        ModelAndView model = new ModelAndView();
        model.addObject("xia", "xiapeixin");
        model.setViewName("fram/index");
        return model;
    }

//    @RequestParam用来接收:
//
//    1 用来处理简单的参数绑定
//
//2 用来接收 Content-Type 是   application/x-www-form-urlencoded (这种格 式的数据例如 user=1234&pwd=1234)编码的内容,这是浏览器默认的 content-Type(请求内容)格式.
//
//    @RequestBody的用法:
//
//    1用来处理不是 application/x-www-form-urlencoded 编码的内容例如:application/json  application/xml

}
