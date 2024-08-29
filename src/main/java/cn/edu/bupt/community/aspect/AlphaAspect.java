package cn.edu.bupt.community.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AlphaAspect {

    @Pointcut("execution(* cn.edu.bupt.community.service.*.*(..))")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void before() {
        System.out.println("Before pointcut.");
    }

    @After("pointcut()")
    public void after() {
        System.out.println("After pointcut.");
    }

    @AfterReturning("pointcut()")
    public void afterReturning() {
        System.out.println("After returning pointcut.");
    }

    @AfterThrowing("pointcut()")
    public void afterThrowing() {
        System.out.println("After throwing pointcut.");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Around before.");
        Object object = joinPoint.proceed();
        System.out.println("Around after.");
        return object;
    }

}
