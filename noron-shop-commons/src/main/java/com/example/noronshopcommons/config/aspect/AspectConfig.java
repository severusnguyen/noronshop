package com.example.noronshopcommons.config.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class AspectConfig {

    @SneakyThrows
    @Around("@annotation(com.example.noronshopcommons.config.annotation.Log)")
    public Object Log(ProceedingJoinPoint proceedingJoinPoint){
        log.info("Method " + proceedingJoinPoint.getSignature().getName() + " execute");

        Object result = proceedingJoinPoint.proceed();

        log.info("Method " + proceedingJoinPoint.getSignature().getName() + " end");

        return result;
    }

    @AfterThrowing(value = "@annotation(com.example.noronshopcommons.config.annotation.Log)", throwing = "ex")
    public void handleException(JoinPoint joinPoint, Exception ex){
        log.error("Method " + joinPoint.getTarget().toString() + " " + joinPoint.getSignature().getName() + " throw exception " + ex.getMessage());
    }
}
