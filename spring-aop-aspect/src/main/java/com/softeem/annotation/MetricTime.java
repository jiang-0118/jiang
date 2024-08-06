package com.softeem.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title: MetricTime
 * @Author Jiang
 * @Package com.softeem.annotation
 * @Date 2024/7/13 19:25
 * @description:注解类
 */
// 元注解：修饰注解的注解
// 元数据：描述数据的数据
@Retention(RetentionPolicy.RUNTIME)//该注解在运行中可以被读取
@Target({ElementType.METHOD})//该注解可以标记在方法上
public @interface MetricTime {
    String name();
}
