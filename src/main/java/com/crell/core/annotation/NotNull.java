package com.crell.core.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotNull {
    public String value();

    public boolean user() default false;
}
