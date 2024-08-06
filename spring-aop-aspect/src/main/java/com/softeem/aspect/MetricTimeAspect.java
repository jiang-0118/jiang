package com.softeem.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

/**
 * @Title: MetricTimeAspect
 * @Author Jiang
 * @Package com.softeem.service.aspect
 * @Date 2024/7/13 14:07
 * @description: 切面类
 */
@Component //需要被容器扫描到
@Aspect //表达该类为一个切面类
public class MetricTimeAspect {


    /*@Before("pointCut()") //在切点前织入增强业务
    @Pointcut("execution(public * com.softeem.service.*.*(..))") //选择切点
    public void pointCut() {
    }
    public void before() {
        Date start = new Date();
        System.out.println("start = " + start);
    }

    @After("pointCut()") //在切点后织入增强业务
    public void after() {
        Date end = new Date();
        System.out.println("end = " + end);
    }

    //在目标方法返回后执行
    @AfterReturning("pointCut()")
    public void afterReturning() {
        System.out.println("已执行完该方法");
    }

    //在目标方法出现异常后执行
    @AfterThrowing(pointcut = "pointCut()", throwing = "ex")
    public void afterReturning(Exception ex) {
        System.out.println("原业务执行异常");
    }*/

    ////    @Around()  环绕增强 包裹住原业务
    /*@Pointcut("execution(public * com.softeem.service.*.*(..))") //选择切点
    public void pointCut() {
    }
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pj) {
        Date start = new Date();
        System.out.println("前置增强 " + start);
        try {
            pj.proceed();
        } catch (Throwable e) {
            System.out.println("原业务执行异常");
            throw new RuntimeException(e);
        } finally {
            Date end = new Date();
            System.out.println("后置增强 " + end);
        }
        System.out.println("返回结果");
        return null;
    }*/
}
