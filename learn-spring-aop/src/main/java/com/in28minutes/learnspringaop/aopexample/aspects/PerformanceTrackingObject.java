package com.in28minutes.learnspringaop.aopexample.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class PerformanceTrackingObject {
    Logger logger = LoggerFactory.getLogger(getClass());

    //    @Around("execution(* com.in28minutes.learnspringaop.aopexample.*.*.*(..))")
    @Around("com.in28minutes.learnspringaop.aopexample.aspects.CommonPointcutConfig.trackTimeAnnotations()") // only for methods that have TrackTime annotation
    public Object findExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // Start a timer
        long startTimeMillis = System.currentTimeMillis();
        // Execute the method
        Object returnValue = proceedingJoinPoint.proceed();

        // Stop the timer
        long stopTimeMillis = System.currentTimeMillis();

        long executionDuration = stopTimeMillis - startTimeMillis;
        logger.info("Around Aspect - {} method executed in {} ms", proceedingJoinPoint, executionDuration);

        return returnValue;
    }
}
