package com.crm.hbdbweb2.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.crm.hbdbweb2.controller.*.*(..))")
    private void forServicePackage() {
    }

    @Pointcut("execution(* com.crm.hbdbweb2.service.*.*(..))")
    private void forControllerPackage() {
    }

    @Pointcut("execution(* com.crm.hbdbweb2.dao.*.*(..))")
    private void forDAOPackage() {
    }

    @Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
    private void forAppFlow() {
    }

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("=====>> in @Before: calling method: " + method);
        Object[] args = joinPoint.getArgs();
        for (Object tempArg : args) {
            logger.info("=====>> argument: " + tempArg);
        }
    }

    @AfterReturning(pointcut = "forAppFlow()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("=====>> in @AfterReturning: calling method: " + method);
        logger.info("=====>> result: " + result);
    }
}
