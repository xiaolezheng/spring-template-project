package com.xxx.xtable.rest.support;

import com.xxx.xtable.api.Result;
import com.xxx.xtable.rest.exception.RequestParamException;
import com.xxx.xtable.service.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionAdvice {
    @ExceptionHandler(RequestParamException.class)
    public Object handler(HttpServletRequest request, RequestParamException ex) {
        log.error("url: {}", request.getRequestURI(), ex);

        return Result.fail(ex.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    public Object handler(HttpServletRequest request, ServiceException ex) {
        log.error("url: {}", request.getRequestURI(), ex);

        return Result.fail(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public Object handler(HttpServletRequest request, RuntimeException ex) {
        log.error("url: {}", request.getRequestURI(), ex);
        return Result.fail(ex.getMessage());
    }

}