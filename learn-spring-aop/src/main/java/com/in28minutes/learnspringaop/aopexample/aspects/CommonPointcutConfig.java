package com.in28minutes.learnspringaop.aopexample.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcutConfig {

    /*
    This is a Spring AOP best practice
     */
    @Pointcut("execution(* com.in28minutes.learnspringaop.aopexample.*.*.*(..))") // now, this can be used everywhere we need both business and data
    public void businessAndDataPackageConfig() {}

    @Pointcut("execution(* com.in28minutes.learnspringaop.aopexample.business.*.*(..))")
    public void businessPackageConfig() {}

    @Pointcut("execution(* com.in28minutes.learnspringaop.aopexample.data.*.*(..))")
    public void dataPackageConfig() {}

    @Pointcut("bean(*Service*)") // any Bean which contains Service will be included here
    public void configUsingBean() {}

    @Pointcut("@annotation(com.in28minutes.learnspringaop.aopexample.annotations.TrackTime)")
    public void trackTimeAnnotations(){

    }
}
