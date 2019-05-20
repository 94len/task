package com.erafollower.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @describe
 * @auth len
 * @createTime 2019/5/20
 */
@Controller
public class TestController {

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "index.html";
    }
}
