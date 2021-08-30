package com.yxx.ticketing.web.converter;

import java.lang.annotation.*;

/**
 *
 *
 *
 * 自定义注解类，获取session中的登陆用户信息
 */


@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginUser {
}
