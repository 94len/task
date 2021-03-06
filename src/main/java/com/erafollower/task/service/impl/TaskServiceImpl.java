package com.erafollower.task.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.erafollower.task.mapper.TaskMapper;
import com.erafollower.task.model.po.Task;
import com.erafollower.task.service.ITaskService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务表 服务实现类
 * </p>
 *
 * @author len
 * @since 2019-05-16
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {

}
