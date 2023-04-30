package com.hb.auth.aspect;

/*@Aspect
@Component
public class ToLowerCaseAspect {
    @Before("execution(* com.hb.blog.controller.UserController.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void convertToLowerCase(JoinPoint joinPoint) {
        System.out.println("*********************");
        System.out.println("*********************");
        System.out.println("*********************");
        System.out.println("*********************");
        System.out.println("*********************");
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof String) {
                args[i] = ((String) args[i]).toLowerCase();
            }
        }
    }
}*/
