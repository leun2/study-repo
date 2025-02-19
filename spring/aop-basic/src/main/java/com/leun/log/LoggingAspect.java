package com.leun.log;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(getClass());

//    @Pointcut("execution(* PACKAGE.*.*(..))")
//    @Before("execution(* com/leun.*.*(..))")
//    public void logMethodCall(JoinPoint joinPoint) {
//        logger.info("Before Aspect - Method is called - {}", joinPoint);
//    }

    @Pointcut("execution(* com.leun..*(..))")
    public void allMethodsInPackage() {}

    @Before("allMethodsInPackage()")
    public void logMethodCall(JoinPoint joinPoint) {
        logger.info("Before Aspect - {} is called with arguments: {}", joinPoint, joinPoint.getArgs());
    }
}
