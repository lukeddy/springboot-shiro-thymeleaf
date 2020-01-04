package com.luke.controller.error;

import com.luke.exception.BookNotFoundException;
import com.luke.exception.SystemException;
import com.luke.exception.UserNotFoundException;
import com.luke.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 用于处理API端异常
 * 使用RestControllerAdvice
 */
@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public Result systemError(SystemException e){
        log.error("error:",e);
        return Result.fail("服务端异常！");
    }

    @ExceptionHandler(BookNotFoundException.class)
    public Result bookError(BookNotFoundException e){
        log.error("error:",e);
        return Result.fail("书不存在！");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public Result userError(UserNotFoundException e){
        log.error("error:",e);
        return Result.fail("用户不存在");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result noHandlerError(NoHandlerFoundException e){
        log.error("error:",e);
        return Result.fail("API请求路径不存在");
    }
}
