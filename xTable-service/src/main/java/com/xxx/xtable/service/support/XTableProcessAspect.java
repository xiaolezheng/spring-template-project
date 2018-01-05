package com.xxx.xtable.service.support;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class XTableProcessAspect {
    @Around("@annotation(LogProfiling)")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object result = pjp.proceed();
            return result;
        } finally {
            log.info("run method:{}, cost:{}", pjp.getSignature().toShortString(), System.currentTimeMillis() - start);
        }
    }
}
