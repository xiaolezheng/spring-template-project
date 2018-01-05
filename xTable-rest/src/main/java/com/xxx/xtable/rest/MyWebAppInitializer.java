package com.xxx.xtable.rest;

import com.xxx.xtable.rest.config.WebConfig;
import com.xxx.xtable.rest.support.LastFilter;
import com.xxx.xtable.service.config.RootConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * webApp 初始化入口,必须保证web服务器支持Servlet 3.0标准（如 tomcat 7 或更高版本）
 */
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/api/*"};
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{new CharacterEncodingFilter("UTF-8"),
                new LastFilter()
        };
    }
}
