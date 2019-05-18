package com.erafollower.task.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.erafollower.task.mapper.UserMapper;
import com.erafollower.task.model.po.User;
import com.erafollower.task.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author len
 * @since 2019-05-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
