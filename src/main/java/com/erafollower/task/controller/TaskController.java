package com.erafollower.task.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.erafollower.task.model.po.Task;
import com.erafollower.task.model.po.TaskRemind;
import com.erafollower.task.service.ITaskRemindService;
import com.erafollower.task.service.ITaskService;
import com.erafollower.task.task.MyTask;
import com.erafollower.task.util.response.ResponseHelper;
import com.erafollower.task.util.response.ResponseModel;
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
    private ITaskService taskService;


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

    //后台获取事务列表
    @GetMapping("listAllTask")
    public ResponseModel listAllTask(){
        List taskList = taskService.selectList(null);
        return ResponseHelper.buildResponseModel(taskList);
    }

    //我的所有事务列表
    @GetMapping("listAllMyTask")
    public ResponseModel listAllMyTask(Integer userId){
        List taskList = taskService.selectList(new EntityWrapper<Task>().eq("user_id",userId));
        return ResponseHelper.buildResponseModel(taskList);
    }


}
