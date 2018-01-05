package com.xxx.xtable.service.support;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ApplicationContextUtil extends ApplicationObjectSupport {

    private static ApplicationContext instance;

    public static ApplicationContext getContext() {
        return instance;
    }

    @PostConstruct
    private void init() {
        instance = getApplicationContext();
    }

}