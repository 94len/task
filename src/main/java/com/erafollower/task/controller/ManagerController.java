package com.erafollower.task.controller;

import com.erafollower.task.model.po.SystemUser;
import com.erafollower.task.service.ISystemUserService;
import com.erafollower.task.util.Encrypt;
import com.erafollower.task.util.response.ResponseHelper;
import com.erafollower.task.util.response.ResponseModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ISystemUserService systemUserService;

    @RequestMapping("/registSystemAccount")
    public @ResponseBody
    ResponseModel registSystemAccount(String nickName, String account, String password){
        if(null == account || null == password){
            return ResponseHelper.systemError("参数错误：account>>["+account+"] password>>["+password+"]");
        }
        SystemUser systemUser = new SystemUser();
        systemUser.setNickName(nickName);
        systemUser.setAccount(account);
        systemUser.setPassword(Encrypt.encryptStr(password));
        systemUserService.insert(systemUser);
        return ResponseHelper.buildResponseModel(systemUser);
    }

    @GetMapping("/login")
    public @ResponseBody ResponseModel login(String account, String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(account,password);
        //进行验证，这里可以捕获异常，然后返回对应信息
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e){
            e.printStackTrace();
            ResponseModel responseModel = ResponseHelper.noLogin("登录失败，帐号或密码错误");
            responseModel.setStatus(1001);//1001密码错误状态码
            return responseModel;
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            ResponseModel responseModel = ResponseHelper.noLogin("登录失败，帐号或密码错误");
            responseModel.setStatus(1001);//1001密码错误状态码
            return responseModel;
        }catch (AuthenticationException e) {
            e.printStackTrace();
            return ResponseHelper.systemError("login failed");
        }
        return ResponseHelper.buildResponseModel(null);
    }


    @GetMapping("/test")
    public ResponseModel test(){
        return ResponseHelper.buildResponseModel("test success!");
    }


    @RequestMapping("/toIndex")
    public String toIndex(){
        return "index";
    }

}


