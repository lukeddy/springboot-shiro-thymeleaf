package com.luke.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/unAuth")
    public String unAuthPage(){
        return "un-auth";
    }

    /**
     * 登录逻辑处理
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public String doLogin(@RequestParam("username") String username,
                          @RequestParam("password")String password,
                          Model model){
        //使用Shiro编写认证逻辑
        //1.获取Subject
        Subject subject= SecurityUtils.getSubject();

        //封装用户数据
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);

        try{
            subject.login(token);
            return "redirect:/home";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户名不存在！！");
            return "login";
        }catch(IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }


    /**
     * 自定义登出
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session, Model model) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        model.addAttribute("msg","安全退出！");
        return "login";
    }

    /**
     * 模拟服务端发生异常
     */
    @RequestMapping("/testError")
    public void handleRequest() {
        throw new RuntimeException("test exception");
    }
}
