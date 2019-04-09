package com.erafollower.task.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.erafollower.task.mapper.JobEntityMapper;
import com.erafollower.task.model.po.JobEntity;
import com.erafollower.task.service.IJobEntityService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author len
 * @since 2019-04-09
 */
@Service
public class JobEntityServiceImpl extends ServiceImpl<JobEntityMapper, JobEntity> implements IJobEntityService {

}
