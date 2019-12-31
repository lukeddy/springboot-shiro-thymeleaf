package com.luke.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request){
        //获取statusCode:401,404,500
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        request.setAttribute("statusCode",statusCode);
        if(null!=exception){
            log.error("error:",exception);
            request.setAttribute("error",exception.getMessage());
        }
        if(statusCode == 401){
            return "/error-page";
        }else if(statusCode == 404){
            return "/error-page";
        }else if(statusCode == 403){
            return "/error-page";
        }else{
            return "/error-page";
        }

    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
