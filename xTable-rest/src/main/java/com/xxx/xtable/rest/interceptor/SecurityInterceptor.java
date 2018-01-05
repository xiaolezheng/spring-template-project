package com.xxx.xtable.rest.interceptor;

import com.xxx.xtable.service.config.SecretKey;
import com.xxx.xtable.service.support.ApplicationContextUtil;
import com.xxx.xtable.service.support.ContextHolder;
import com.xxx.xtable.service.support.Principal;
import com.xxx.xtable.service.util.HttpUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SecretKey secretKey = ApplicationContextUtil.getContext().getBean(SecretKey.class);
        Principal principal = HttpUtil.fromRequest(request, secretKey.getSecretKey());
        ContextHolder.set(Principal.class, principal);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ContextHolder.remove(Principal.class);
    }
}
