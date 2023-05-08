package com.hb.auth.aspect;

import com.hb.auth.validator.ObjectValidator;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class ValidatorAspect {
    @Around("@annotation(org.springframework.web.bind.annotation.PostMapping) || "
            + "@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public Object validateRequestBody(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg != null) {
                ObjectValidator.validate(arg);
            }
            /*if (arg != null && arg.getClass().isAnnotationPresent(RequestBody.class)) {
                ObjectValidator.validate(arg);
            }*/

        }
        return joinPoint.proceed();
    }
}
