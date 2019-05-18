package com.erafollower.task.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.erafollower.task.mapper.SystemUserMapper;
import com.erafollower.task.model.po.SystemUser;
import com.erafollower.task.service.ISystemUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author len
 * @since 2019-05-16
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements ISystemUserService {

}
