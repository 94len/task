package com.erafollower.task.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.erafollower.task.model.po.Task;
import com.erafollower.task.model.po.TaskRemind;
import com.erafollower.task.service.ITaskRemindService;
import com.erafollower.task.service.ITaskService;
import com.erafollower.task.util.IdGeneratorBySnowflake;
import com.erafollower.task.util.response.ResponseHelper;
import com.erafollower.task.util.response.ResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class TaskController {

    @Autowired
    private ITaskRemindService taskRemindService;
    @Autowired
    private ITaskService taskService;


    @GetMapping("/myTaskList")
    public ResponseModel myTaskList (Integer userId){
        List taskList = taskService.selectList(new EntityWrapper<Task>().eq("user_id",userId));
        return ResponseHelper.buildResponseModel(taskList);
    }

    @GetMapping("listAllTask")
    public ResponseModel listAllTask(){
        List taskList = taskService.selectList(null);
        return ResponseHelper.buildResponseModel(taskList);
    }

    @GetMapping("listAllMyTask")
    public ResponseModel listAllMyTask(Integer userId){
        List taskList = taskService.selectList(new EntityWrapper<Task>().eq("user_id",userId));
        return ResponseHelper.buildResponseModel(taskList);
    }

    @PostMapping("/addTask")
    public ResponseModel addTask(@RequestBody Task task){
        Date now = new Date();
        task.setId(IdGeneratorBySnowflake.newInstance.nextId());
        try {
            if(taskService.insertOrUpdate(task)){
               return ResponseHelper.buildResponseModel(task);
            }else{
               return ResponseHelper.systemError("create task error");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseHelper.systemError("create task error");
        }
    }

    @PostMapping("/updateTask")
    public ResponseModel updateTask(@RequestBody Task task){
        if(task.getId() == null){
            return ResponseHelper.systemError("PARAM ERROR");
        }
        boolean result = taskService.updateById(task);
        return ResponseHelper.buildResponseModel(result);
    }



}
