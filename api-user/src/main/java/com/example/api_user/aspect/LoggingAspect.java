package com.example.api_user.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.example.api_user..*(..))") // Monitors all methods in the com.example.api_user package
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis(); // Start time for method execution
        Object result;

        try {
            // Logging method entry with arguments
            logger.info("Method called: {} with arguments: {}", joinPoint.getSignature(), joinPoint.getArgs());

            // Execute the target method
            result = joinPoint.proceed();

            // Logging method exit with result
            logger.info("Method finished: {} with result: {}", joinPoint.getSignature(), result);
        } finally {
            // Logging the execution time
            long duration = System.currentTimeMillis() - startTime;
            logger.info("Execution time for method {}: {} ms", joinPoint.getSignature(), duration);
        }

        return result;
    }
}