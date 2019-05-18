package com.erafollower.task.task;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.erafollower.task.model.po.Task;
import com.erafollower.task.model.po.TaskRemind;
import com.erafollower.task.model.po.User;
import com.erafollower.task.service.ITaskRemindService;
import com.erafollower.task.service.ITaskService;
import com.erafollower.task.service.IUserService;
import com.erafollower.task.util.EmailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

/**
 * @describe
 * @auth len
 * @createTime 2019/5/16
 */
@Component
@Slf4j
public class ScheduleTask {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    @Autowired
    private ITaskRemindService taskRemindService;
    @Autowired
    private ITaskService taskService;
    @Autowired
    private IUserService userService;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    freemarker.template.Configuration freemarkerConfig;

    private ScheduledFuture<?> future;
    private List<TaskRemind> taskReminds;

    private class MyRunnable implements Runnable {
        private TaskRemind taskRemind;
        private Task task = null;
        private User user = null;
        private String phone = null;

        @Override
        public void run() {
            taskRemind.setIsVaild(0);
            taskRemindService.updateById(taskRemind);
            //根据条件判断提醒方式
            task = taskService.selectById(taskRemind.getTaskId());
            user = userService.selectById(task.getUserId());
            //发送短信
            if (task.getRemindWay().contentEquals("1")) {
                phone = user.getPhoneNum();
                //todo
            }
            //发送邮件
            if (task.getRemindWay().contentEquals("2")) {
                try {
                    new EmailUtil().sendEmail(mailSender, freemarkerConfig, user.getEmail(), user.getNickName(), task.getContent());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    public void startCron(MyRunnable runnable, String cron) {
        future = threadPoolTaskScheduler.schedule(runnable, new CronTrigger(cron));
    }

    public void stopCron() {
        if (future != null) {
            future.cancel(true);
        }
    }

    //每隔1分钟执行任务
    //@Scheduled(fixedRate = 60000)
    public void toScanData() {
        log.info("######## 每隔一分钟扫描数据库 ########");
        taskReminds = taskRemindService.selectList(new EntityWrapper<TaskRemind>().eq("is_vaild", 1));
        for (TaskRemind remind : taskReminds) {
            //怎么避免重复开启一条记录的定时任务？
            MyRunnable runnable = new MyRunnable();
            runnable.taskRemind = remind;
            this.startCron(runnable, remind.getCron());
        }

    }

}
