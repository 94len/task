package com.erafollower.task.config;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.erafollower.task.model.po.TaskRemind;
import com.erafollower.task.service.ITaskRemindService;
import com.erafollower.task.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.util.List;

/**
 * @describe
 * @auth len
 * @createTime 2019/5/15
 */
//@Component      //1.主要用于标记配置类，兼备Component的效果。
public class DynamicScheduleTask implements SchedulingConfigurer {

    @Autowired
    private ITaskService taskService;
    @Autowired
    private ITaskRemindService taskRemindService;

    private List<TaskRemind> taskReminds;

    private ScheduledTaskRegistrar taskRegistrarc;
    /**
     * 执行定时任务.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        System.out.println("############configureTasks");
        System.out.println("############taskRegistrar"+taskRegistrar);
        //taskReminds = taskRemindService.selectList(new EntityWrapper<TaskRemind>().eq("is_vaild",1));
        if(taskRegistrarc == null){
            taskRegistrarc = taskRegistrar;
        }
        System.out.println("############taskRegistrarc"+taskRegistrarc);
        if(null != taskReminds){
            for (TaskRemind taskRemind : taskReminds) {
                if(taskRemind.getIsVaild() == 0){
                    continue;
                }
                taskRegistrarc.addTriggerTask(
                        //1.添加任务内容(Runnable)
                        () -> new Runnable(){
                            @Override
                            public void run() {
                                System.out.println("############执行任务: ");
                            }
                        },
                        //2.设置执行周期(Trigger)
                        triggerContext -> {
                            //2.1 从数据库获取执行周期
                            //2.2 合法性校验.
                            if (StringUtils.isEmpty("0/5 * * * * ?")) {
                                // Omitted Code ..
                            }
                            //2.3 返回执行周期(Date)
                            return new CronTrigger("0/5 * * * * ?").nextExecutionTime(triggerContext);
                        }
                );
            }
        }


    }

    public void addTask(List<TaskRemind> taskReminds){
        System.out.println("############addTask");
        this.taskReminds = taskReminds;
        this.configureTasks(taskRegistrarc);
        taskRegistrarc.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> new Runnable(){
                    @Override
                    public void run() {
                        System.out.println("############执行任务: ");
                    }
                },
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty("0/5 * * * * ?")) {
                        // Omitted Code ..
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger("0/5 * * * * ?").nextExecutionTime(triggerContext);
                }
        );
    }

}
