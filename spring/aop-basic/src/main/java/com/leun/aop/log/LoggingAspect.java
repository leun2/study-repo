    package com.leun.aop.log;


    import org.aspectj.lang.JoinPoint;
    import org.aspectj.lang.annotation.After;
    import org.aspectj.lang.annotation.AfterReturning;
    import org.aspectj.lang.annotation.AfterThrowing;
    import org.aspectj.lang.annotation.Aspect;
    import org.aspectj.lang.annotation.Before;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.context.annotation.Configuration;

    @Aspect
    @Configuration
    public class LoggingAspect {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        @Before("com.leun.aop.CommonPointcuts.allMethodsInPackage()")
        public void logMethodCallBeforeExecution(JoinPoint joinPoint) {
            logger.info("Before Aspect - {} is called with arguments: {}", joinPoint, joinPoint.getArgs());
        }

        @After("com.leun.aop.CommonPointcuts.allMethodsInPackage()")
        public void logMethodCallAfterExecution(JoinPoint joinPoint) {
            logger.info("After Aspect - {} has executed", joinPoint);
        }

        @AfterThrowing(pointcut = "com.leun.aop.CommonPointcuts.allMethodsInPackage()", throwing = "exception")
        public void logMethodCallAfterException(JoinPoint joinPoint, Throwable exception) {
            logger.error("AfterThrowing Aspect - {} has thrown an exception: {}", joinPoint, exception.getMessage(), exception);
        }

        @AfterReturning(pointcut = "com.leun.aop.CommonPointcuts.allMethodsInPackage()", returning = "resultValue")
        public void logMethodCallAfterSuccessfulExecution(JoinPoint joinPoint, Object resultValue) {
            logger.info("AfterReturning Aspect - {} has returned: {}", joinPoint, resultValue);
        }
    }