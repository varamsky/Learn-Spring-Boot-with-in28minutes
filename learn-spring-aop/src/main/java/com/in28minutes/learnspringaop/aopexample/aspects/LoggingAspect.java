package com.in28minutes.learnspringaop.aopexample.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect // defines that this would contain AOP configuration
public class LoggingAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());

    //PointCut - When to do?
    @Before("com.in28minutes.learnspringaop.aopexample.aspects.CommonPointcutConfig.businessAndDataPackageConfig()") // Before the execution of the methods mentioned via the package we can execute specific code as we have executed the simple logs here
    public void logMethodCallBeforeExecution(JoinPoint joinPoint) {
        // Logic - What to do?
        logger.info("Before Aspect - {} is called with arguments: {}", joinPoint, joinPoint.getArgs());
    }

    @After("execution(* com.in28minutes.learnspringaop.aopexample.*.*.*(..))")
    public void logMethodCallAfterExecution(JoinPoint joinPoint) {
        logger.info("After Aspect - {} has executed", joinPoint);
    }

    @AfterThrowing(pointcut = "execution(* com.in28minutes.learnspringaop.aopexample.*.*.*(..))", throwing = "exception")
    public void logMethodCallAfterThrowing(JoinPoint joinPoint, Exception exception) { // the variable name must be same as the "throwing" argument value in the above annotation
        logger.info("AfterThrowing Aspect - {} has thrown an exception {}", joinPoint, exception);
    }

    @AfterReturning(pointcut = "execution(* com.in28minutes.learnspringaop.aopexample.*.*.*(..))", returning = "resultValue")
    public void logMethodCallAfterReturning(JoinPoint joinPoint, Object resultValue) { // the variable name must be same as the "returning" argument value in the above annotation
        logger.info("AfterReturning Aspect - {} has returned {}", joinPoint, resultValue);
    }
}
