package com.hb.auth.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    /**
     *This AOP allow to execute Advice on package and its classes and methods
     */
    @Pointcut("execution(* com.hb.auth.controller.*.*(..))")
    public void loggingPointCut() {
    }

    @Before("loggingPointCut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("====Before Evey Methode Invoke in Controllers ====>" + joinPoint.getSignature());
    }

    @After("loggingPointCut()")
    public void after(JoinPoint joinPoint) {
        System.out.println("====After Every Methode Invoke in Controllers ====>" + joinPoint.getSignature());
    }

    /*@Before("execution(* com.hb.blog.controller.UserController.create(..))" +
            "&& args(requestBody,..)")
    public void printRequestBody(JoinPoint joinPoint, Object requestBody) {
        if (requestBody instanceof CreateUserRequest request) {
            System.out.println("============= Before Create User Request ==============");
            System.out.println(request);
            System.out.println("=======================================================");
        }
    }

    @Around("loggingPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("========Before Every Methode Invoke in Controllers ==========");
        System.out.println(joinPoint.getArgs()[0]);
        System.out.println("============================================================");

        Object object = joinPoint.proceed();
        if (object instanceof ResponseEntity<?>) {
            ResponseEntity<UserResponse> userResponse = (ResponseEntity<UserResponse>) object;
            System.out.println("=====After Create User Methode Invoke in Controllers ======");
            System.out.println(userResponse);
            System.out.println("============================================================");
        }
        return object;
    }*/

    /**
     *This AOP allow to execute Advice on specific package
     */
    /*@Pointcut("within(com.hb.blog.service.*)")
    public void loggingServicePointCut() {
    }
    @Before("loggingServicePointCut()")
    public void beforeService(JoinPoint joinPoint) {
        System.out.println("========Before Evey Methode Invoke in Service package ==========");
        System.out.println(joinPoint.getSignature());
        System.out.println("============================================================");
    }*/

    /**
     *This AOP allow to execute Advice on specific Class
     */
    /*@Pointcut("within(com.hb.blog.service.UserService)")
    public void loggingUserServicePointCut() {
    }

    @After("loggingUserServicePointCut()")
    public void afterUserService(JoinPoint joinPoint) {
        System.out.println("========After Every Methode Invoke in User Service ==========");
        System.out.println(joinPoint.getSignature());
        System.out.println("============================================================");
    }*/

    /**
     *This AOP allow to execute Advice based on Methods or Fields that contains @Annotation
     */
    /*@Pointcut("@annotation(com.hb.blog.annotation.CustomAnnotation)")
    public void loggingAnnotationPointCut() {
    }
    @After("loggingAnnotationPointCut()")
    public void afterCustomAnnotation(JoinPoint joinPoint) {
        System.out.println("=====After Every Methode Contains @CustomAnnotation =======");
        System.out.println(joinPoint.getSignature());
        System.out.println("============================================================");
    }*/

    /**
     *This AOP allow to execute Advice based on Methods and @Annotation
     */
    /*@Before("execution(* com.hb.blog.controller.UserController.search(..))" +
            "&& @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void queryParamsToLowerCase(JoinPoint joinPoint) {
        System.out.println(Arrays.toString(joinPoint.getArgs()));
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof String) {
                args[i] = ((String) args[i]).toLowerCase();
            }
        }
    }*/

/*    @Around("execution(* com.hb.blog.controller.UserController.search(..))")
    public Object convertToLowerCase(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");

        if (firstName != null) request.setAttribute("firstName", firstName.toLowerCase());
        if (lastName != null) request.setAttribute("lastName", lastName.toLowerCase());

        return joinPoint.proceed();
    }*/

    // Lower Case the values of form data before it reach the search controller
}
