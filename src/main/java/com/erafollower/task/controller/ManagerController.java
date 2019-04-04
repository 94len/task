package com.erafollower.task.controller;

import com.erafollower.task.model.po.SystemUser;
import com.erafollower.task.service.ISystemUserService;
import com.erafollower.task.util.response.ResponseHelper;
import com.erafollower.task.util.response.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ISystemUserService systemUserService;

    @RequestMapping("/registSystemAccount")
    public ResponseModel registSystemAccount(String nickName, String account, String password){
        if(null == account || null == password){
            return ResponseHelper.systemError("参数错误：account>>["+account+"] password>>["+password+"]");
        }
        SystemUser systemUser = new SystemUser();
        systemUser.setNickName(nickName);
        systemUser.setAccount(account);
        systemUser.setPassword(password);
        systemUserService.insert(systemUser);
        return ResponseHelper.buildResponseModel(systemUser);
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


