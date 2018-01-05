package com.xxx.xtable.rest.interceptor;

import com.xxx.xtable.service.support.ContextHolder;
import com.xxx.xtable.service.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LogInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        long start = System.currentTimeMillis();
        ContextHolder.set(Long.class, start);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        long start = ContextHolder.remove(Long.class);
        long end = System.currentTimeMillis();
        long cost = end - start;
        String ip = HttpUtil.getIpAddress(httpServletRequest);
        String requestUri = httpServletRequest.getRequestURI();

        log.info("request,ip:{},url:{},cost:{}", ip, requestUri, cost);
    }
}
