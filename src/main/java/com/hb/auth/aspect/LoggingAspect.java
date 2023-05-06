package com.hb.auth.aspect;

import com.hb.auth.payload.request.user.CreateUserRequest;
import com.hb.auth.payload.response.user.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    /**
     * This AOP allow to execute Advice on package and its classes and methods
     */
    @Pointcut("execution(* com.hb.auth.controller.*.*(..))")
    public void loggingPointCut() {
    }

    @Before("loggingPointCut()")
    public void before(JoinPoint joinPoint) {
        log.info("== Before all Methods in Controller package ==> " + joinPoint.getSignature());
    }

    @After("loggingPointCut()")
    public void after(JoinPoint joinPoint) {
        log.info("== After all Methods in Controller package ==> " + joinPoint.getSignature());
    }

    @Before("execution(* com.hb.auth.controller.UserController.create(..)) && args(requestBody,..)")
    public void printRequestBody(Object requestBody) {
        if (requestBody instanceof CreateUserRequest request) {
            log.info("== Before Create User Request ==> " + request);
        }
    }

    @Around("loggingPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("== Before Every Methode Invoke in Controllers ==> " + joinPoint.getArgs()[0]);

        Object object = joinPoint.proceed();
        if (object instanceof ResponseEntity<?> && ((ResponseEntity<?>) object).getBody() instanceof UserResponse response) {
            log.info("== After successful registration of user ==> " + response);
        }
        return object;
    }

    /**
     * within(com.hb.auth.service.*) AOP allow to execute Advice on specific package
     * within(com.hb.auth.service.UserService) AOP allow to execute Advice on specific class
     */
    @Pointcut(value = "within(com.hb.auth.service.*)")
    public void loggingServicePointCut() {
    }
    @Before("loggingServicePointCut()")
    public void beforeService(JoinPoint joinPoint) {
        log.info("== Within Service package ==> " + joinPoint.getSignature());
    }

    /**
     * This AOP allow to execute Advice based on Methods or Fields that contains @Annotation
     */
    @Pointcut("@annotation(com.hb.auth.annotation.CustomAnnotationForAspect)")
    public void loggingAnnotationPointCut() {
    }

    @After("loggingAnnotationPointCut()")
    public void afterCustomAnnotation(JoinPoint joinPoint) {
        log.info("== After Methods Contains @CustomAnnotationForAspect ==> " + joinPoint.getSignature());
    }

    /**
     * This AOP allow to execute Advice based on Methods and @Annotation
     * Can be used as middleware to lowercase user inputs for example
     */
    @Before("execution(* com.hb.auth.controller.UserController.search(..))" +
            "&& @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void beforeSearchAndGetMethod(JoinPoint joinPoint) {
        log.info(Arrays.toString(joinPoint.getArgs()));
    }
}
