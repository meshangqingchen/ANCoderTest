package com.example.ancodertest.zhujie;


import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD,ElementType.CONSTRUCTOR,ElementType.LOCAL_VARIABLE,ElementType.PARAMETER,ElementType.FIELD})
public @interface TestA {
    String name();
    int id() default 0;
    Class gid();
}
