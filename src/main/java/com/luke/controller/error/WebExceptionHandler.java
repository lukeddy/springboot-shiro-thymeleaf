package com.luke.controller.error;

import com.luke.exception.BookNotFoundException;
import com.luke.exception.SystemException;
import com.luke.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 用于处理网页端异常
 */
@Slf4j
@ControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public String systemError(SystemException e){
        log.error("error:",e);
        return "error/500";
    }

    @ExceptionHandler(BookNotFoundException.class)
    public String bookError(BookNotFoundException e){
        log.error("error:",e);
        return "error/404";
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String userError(UserNotFoundException e){
        log.error("error:",e);
        return "error/404";
    }

//    @ExceptionHandler(NoHandlerFoundException.class)
//    public String noHandlerError(NoHandlerFoundException e){
//        log.error("error:",e);
//        return "error/unknow-error";
//    }
}
