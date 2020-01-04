package com.luke.controller;

import com.luke.exception.UserNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/add")
    public String add(){
        return "add";
    }

    @GetMapping("/update")
    public String update(){
        return "update";
    }

    @GetMapping("/other")
    public String other(){
        return "other";
    }

    @GetMapping("/testUserNotExist")
    public String notExist(){
        throw new UserNotFoundException("用户找不到异常");
    }

}
