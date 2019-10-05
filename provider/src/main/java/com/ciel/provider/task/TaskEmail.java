package com.ciel.provider.task;

import com.ciel.pojo.App;
import com.ciel.service.AppServicer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


@Component
public class TaskEmail {

    @Autowired
    private AppServicer appServicer;

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void run(){  //这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行
        System.out.println("hello ");
    }

    //, 枚举; -区间 ; 0/4 从0秒启动,每4秒执行一次 ;  1 1 1 LW * ? 每个月最后一个工作日
    // (星期写法)1-7,1周日,7 周六 ; 4#2 第二个星期4; 7L每个月最后一个周六
    @Scheduled(cron = "1-2 * * 14,15 * 1-7" )
    public void run2() throws FileNotFoundException, MessagingException {
//        List<App> list = appServicer.list();
//        System.err.println(list);

//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setSubject("日志清洗通知");
//        simpleMailMessage.setText("将于今晚开启种族清洗\n" +
//                "杀光异教徒");
//
//        simpleMailMessage.setTo("1018866480@qq.com");
//        //simpleMailMessage.setCc(); //抄送
//        simpleMailMessage.setFrom("15966504931@163.com"); //自己, 设置邮件发送者
//        javaMailSender.send(simpleMailMessage);


//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
//        mimeMessageHelper.setSubject("上她");
//        mimeMessageHelper.setText("<h6 style='color:red'>上她</h6>",true);
//        mimeMessageHelper.setTo("1018866480@qq.com");
//        mimeMessageHelper.setFrom("15966504931@163.com"); //自己, 设置邮件发送者
//        mimeMessageHelper.addAttachment("she.jpg",new File("c:/ciel/met-art_ekl_5_0004.jpg"));
//
//        javaMailSender.send(mimeMessage);
    }
}
