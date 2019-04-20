package com.erafollower.task.task;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.erafollower.task.model.po.Task;
import com.erafollower.task.model.po.TaskRemind;
import com.erafollower.task.model.po.User;
import com.erafollower.task.service.ITaskRemindService;
import com.erafollower.task.service.ITaskService;
import com.erafollower.task.service.IUserService;
import com.erafollower.task.util.Encrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @describe
 * @auth len
 * @createTime 2019/4/10
 */
@Component
@Slf4j
public class MyTask {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    freemarker.template.Configuration freemarkerConfig;
    @Autowired
    private ITaskRemindService taskRemindService;
    @Autowired
    private ITaskService taskService;
    @Autowired
    private IUserService userService;

    //当前时间戳
    Long now = null;

    List<TaskRemind> taskReminds = null;
    Task task = null;
    User user = null;
    String phone = null;
    String email = null;
    SimpleMailMessage mailMessage = null;
    //每隔1分钟执行任务
    @Scheduled(fixedRate = 60000)
    public void toScanData(){
        now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        taskReminds = taskRemindService.selectList(new EntityWrapper<TaskRemind>().between("remind_time",now,now+70000));
        if(null != taskReminds && taskReminds.size() > 0){
            //获取task
            for (TaskRemind taskRemind : taskReminds) {
                if(taskRemind.getIsSend() != null && taskRemind.getIsSend() == 1){
                    continue;
                }
                task = taskService.selectById(taskRemind.getTaskId());
                user =  userService.selectById(task.getUserId());
                //发送短信
                if(task.getRemindWay().contentEquals("1")){
                    phone = user.getPhoneNum();
                }
                //发送邮件
                if(task.getRemindWay().contentEquals("2")){
                    try {
                        this.sendEmail(taskRemind,user.getEmail(),user.getNickName(),LocalDateTime.ofEpochSecond(task.getRemindTime()/1000,0,ZoneOffset.ofHours(8)),task.getContent());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void sendEmail(TaskRemind taskRemind,String toEmail, String userName, LocalDateTime remindTime,String content) throws Exception {
        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("891846581@qq.com");
        helper.setTo(toEmail);//1822948363@qq.com
        helper.setSubject("【25点】定时提醒");
        //设置替换的参数对象
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("userName", StringUtils.isEmpty(userName) ? "用户" : userName);
        model.put("remindTime",dtFormat.format(remindTime));
        model.put("content",content);
        String templateString = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfig.getTemplate("emailTempl.ftl"), model);
        helper.setText(templateString,true);
        try {
            mailSender.send(mimeMessage);
            taskRemind.setIsSend(1);
            taskRemindService.updateById(taskRemind);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }


}
