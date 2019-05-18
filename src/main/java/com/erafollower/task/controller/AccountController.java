package com.erafollower.task.controller;

import com.erafollower.task.model.po.SystemUser;
import com.erafollower.task.model.po.User;
import com.erafollower.task.model.vo.RegistUserVo;
import com.erafollower.task.service.ISystemUserService;
import com.erafollower.task.service.IUserService;
import com.erafollower.task.util.Encrypt;
import com.erafollower.task.util.response.ResponseHelper;
import com.erafollower.task.util.response.ResponseModel;
import com.erafollower.task.util.response.StatusCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(description = "帐号管理")
@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "用户注册")
    @PostMapping("/regist")
    public ResponseModel registSystemAccount(RegistUserVo registUserVo){
        if(null == registUserVo.getAccount() || null == registUserVo.getPassword()){
            return ResponseHelper.systemError("参数错误：account>>["+registUserVo.getAccount()+"] password>>["+registUserVo.getPassword()+"]");
        }
        SystemUser systemUser = new SystemUser();
        User user = new User();
        BeanUtils.copyProperties(registUserVo,user);
        userService.insert(user);
        return ResponseHelper.success();
    }

    @ApiOperation("用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account",value = "帐号",dataType = "String"),
            @ApiImplicitParam(name="password",value = "密码",dataType = "String")
    })
    @GetMapping("/login")
    public @ResponseBody ResponseModel login(String account, String password){
        log.info("#########  login  #########");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(account,password);
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e){
            e.printStackTrace();
            return ResponseHelper.error(StatusCodeEnum.LOGIN_FAIL_NOACCOUNT.getStatus(),StatusCodeEnum.LOGIN_FAIL_NOACCOUNT.getMessage());
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            return ResponseHelper.error(StatusCodeEnum.LOGIN_FAIL_PASSWORDERROR.getStatus(),StatusCodeEnum.LOGIN_FAIL_PASSWORDERROR.getMessage());
        }catch (AuthenticationException e) {
            e.printStackTrace();
            return ResponseHelper.systemError("login failed");
        }
        return ResponseHelper.buildResponseModel(null);
    }

//    @RequestMapping("/toIndex")
//    public String toIndex(){
//        return "index";
//    }

}


