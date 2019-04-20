package com.erafollower.task.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.erafollower.task.model.po.TaskRemind;
import com.erafollower.task.service.ITaskRemindService;
import com.erafollower.task.task.MyTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class TaskController {

    @Autowired
    private ITaskRemindService taskRemindService;
    @Autowired
    private MyTask myTask;

    @GetMapping("/test")
    public String test(){
        //当前时间戳
        Long now = Instant.now().toEpochMilli();
        List<TaskRemind> taskReminds = taskRemindService.selectList(new EntityWrapper<TaskRemind>().between(true,"remind_time",now,now+60000));
        log.info(">>>>>>>>>>>>>"+taskReminds);
        return "success";
    }

    @GetMapping("/testSendEmail")
    public String testSendEmail(){
        myTask.toScanData();
        return "success";
    }

}
