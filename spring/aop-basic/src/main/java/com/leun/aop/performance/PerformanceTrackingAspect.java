package com.leun.aop.performance;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class PerformanceTrackingAspect {

    private final Logger logger = LoggerFactory.getLogger(getClass());

//    @Around("com.leun.aop.CommonPointcuts.allServiceComponents()")
    @Around("com.leun.aop.CommonPointcuts.trackTimeAnnotation()")
    public Object findExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTimeMillis = System.currentTimeMillis();

        Object returnValue = proceedingJoinPoint.proceed();

        long stopTimeMillis = System.currentTimeMillis();

        long executionDuration = startTimeMillis - stopTimeMillis;

        logger.info("Around Aspect - {} Method executed in {}", proceedingJoinPoint,
            executionDuration);

        return returnValue;
    }
}
