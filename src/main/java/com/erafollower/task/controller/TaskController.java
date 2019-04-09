package com.erafollower.task.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.erafollower.task.model.po.Task;
import com.erafollower.task.service.ITaskService;
import com.erafollower.task.util.JudgeEmpty;
import com.erafollower.task.util.response.ResponseHelper;
import com.erafollower.task.util.response.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {


    @Autowired
    private ITaskService taskService;


    @RequestMapping("/listTaskFUserId")
    public ResponseModel listTaskFUserId(Integer userId){
        if(null == userId){
            return ResponseHelper.paramError("userId is null");
        }

        List<Task> tasks = taskService.selectList(new EntityWrapper<Task>().eq("user_id",userId));

        if(!JudgeEmpty.listIsEmpty(tasks)){

        }
        return ResponseHelper.buildResponseModel(tasks);

    }

}
