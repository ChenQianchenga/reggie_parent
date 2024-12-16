package com.reggie.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义放行注解
 * 用于解决请求路径相同访问方式不同  get/post
 */
@Target(ElementType.METHOD) //这表示该注解仅能应用在方法上，而不是类或其他元素上
@Retention(RetentionPolicy.RUNTIME) //这表示在运行时可以通过反射获取到这个注解的信息
public @interface IgnoreToken {


}
