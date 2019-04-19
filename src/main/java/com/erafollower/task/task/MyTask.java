package com.erafollower.task.task;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.erafollower.task.model.po.Task;
import com.erafollower.task.model.po.TaskRemind;
import com.erafollower.task.model.po.User;
import com.erafollower.task.service.ITaskRemindService;
import com.erafollower.task.service.ITaskService;
import com.erafollower.task.service.IUserService;
import com.erafollower.task.util.Encrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * @describe
 * @auth len
 * @createTime 2019/4/10
 */
@Component
@Slf4j
public class MyTask {

    @Autowired
    private ITaskRemindService taskRemindService;
    @Autowired
    private ITaskService taskService;
    @Autowired
    private IUserService userService;

    //当前时间戳
    private static final Long now = Instant.now().toEpochMilli();

    //每隔10分钟执行任务
    @Scheduled(fixedRate = 60000)
    public void toScanData(){
        log.info("######## 每隔一分钟执行任务 ########");
        List<TaskRemind> taskReminds = taskRemindService.selectList(new EntityWrapper<TaskRemind>().between("remind_time",now,now+60000));
        if(null != taskReminds && taskReminds.size() > 0){
            //获取task
            Task task = null;
            for (TaskRemind taskRemind : taskReminds) {
                task = taskService.selectById(taskRemind.getTaskId());
                User user = userService.selectById(task.getUserId());
                //发送短信
                if(task.getRemindWay().contentEquals("1")){
                    String phone = user.getPhoneNum();


                }
                //发送邮件
                if(task.getRemindWay().contentEquals("2")){
                    String email = user.getEmail();
                }
            }
        }
    }



}
