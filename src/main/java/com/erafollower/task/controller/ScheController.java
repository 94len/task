package com.erafollower.task.controller;


import com.erafollower.task.util.response.ResponseHelper;
import com.erafollower.task.util.response.ResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;

/**
 * 定时执行的接口
 */
@RestController
@RequestMapping("/sche")
@Slf4j
public class ScheController {


    @Autowired
    private JavaMailSender mailSender;

    /**
     * xxljob应该设置每天八点发送邮件
     * @return
     */
    @GetMapping("/morning")
    public ResponseModel morning(){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom("891846581@qq.com");
            helper.setTo("891846581@qq.com");
            helper.setSubject("该起床了！");

            String content = "o(*￣︶￣*)o为了更好的自己，为了更好的未来! <br>"
                    + "快开始你全新的一天吧";

            helper.setText(content,true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            log.error("发送滤网领取提醒发送错误！");
        }

        return ResponseHelper.success();
    }

    @GetMapping("/strainerSche")
    public ResponseModel strainerSche(){
        LocalDate today = LocalDate.now();
        LocalDate targetDate = LocalDate.of(2020,02,11);
        LocalDate endDate = LocalDate.of(2020,03,11);

        if(today.isBefore(targetDate)){
            return ResponseHelper.buildResponseModel("不在日期2020-02-11~2020-03-11范围之内");
        }else if(today.isAfter(endDate)){
            return ResponseHelper.buildResponseModel("不在日期2020-02-11~2020-03-11范围之内");
        }

        // sendEmail
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom("891846581@qq.com");
            helper.setTo("891846581@qq.com");
            helper.setSubject("滤网领取提醒");

            String content = "任务控制中心提醒您，您的空气净化器需要更换滤网了。<br>"
                    + "滤网领取时间为：2020-02-11~2020-03-11 <br>"
                    + "请联系相关淘宝客服领取。";

            helper.setText(content,true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            log.error("发送滤网领取提醒发送错误！");
        }

        return ResponseHelper.success();

    }
}
