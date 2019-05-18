package com.erafollower.task.util;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.erafollower.task.model.po.TaskRemind;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @describe
 * @auth len
 * @createTime 2019/5/16
 */
public class EmailUtil {

    public void sendEmail(JavaMailSender mailSender, Configuration freemarkerConfig, String toEmail, String userName, String content) throws Exception {
        System.out.println("########## sendEmail #########");
        System.out.println("#########toEmail: "+toEmail+"#############userName: "+userName+"#############content: "+content+"#############");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("891846581@qq.com");
        helper.setTo(toEmail);//1822948363@qq.com
        helper.setSubject("【25点】定时提醒");
        //设置替换的参数对象
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("userName", StringUtils.isEmpty(userName) ? "用户" : userName);
        model.put("remindTime",format.format(new Date()));
        model.put("content",content);
        String templateString = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfig.getTemplate("emailTempl.ftl"), model);
        helper.setText(templateString,true);
        try {
            mailSender.send(mimeMessage);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
}
