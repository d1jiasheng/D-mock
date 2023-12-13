package com.sz.mockbean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author dijiasheng
 * @date 2023/4/21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.LOCAL_VARIABLE})
public @interface MockBean {

    long beanId();

    String beanName() default "";

}
