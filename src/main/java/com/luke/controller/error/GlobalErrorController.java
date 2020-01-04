package com.luke.controller.error;

import com.luke.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class GlobalErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    /**
     * 处理网页异常
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = {ERROR_PATH},produces = {"text/html"})
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        int code = response.getStatus();
        if (code == 404) {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        } else if (code == 403) {
            return new ModelAndView("error/403",HttpStatus.FORBIDDEN);
        } else if (code == 401) {
            return new ModelAndView("login");
        } else {
            return new ModelAndView("error/500",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 处理API错误
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = ERROR_PATH)
    public Result handleError(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(200);
        int code = response.getStatus();
        if (404 == code) {
            return Result.fail( "未找到资源");
        } else if (403 == code) {
            return Result.fail( "没有访问权限");
        } else if (401 == code) {
            return Result.fail( "登录过期");
        } else {
            return Result.fail( "服务器错误");
        }
    }
}
