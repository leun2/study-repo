package com.leun.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CommonPointcuts {

    @Pointcut("execution(* com.leun..*(..))")
    public void allMethodsInPackage(){}

    @Pointcut("bean(*Service*)")
    public void allServiceComponents() {}

    @Pointcut("@annotation(com.leun.aop.TrackTime)")
    public void trackTimeAnnotation() {}
}
