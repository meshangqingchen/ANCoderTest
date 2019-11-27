package com.example.ancodertest.zhujie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@TestA(name = "type1",id=1,gid = List.class)//使用了类注解
public class UserAnnotation {
    @TestA(name = "chengyuan",id=1,gid = Long.class) //使用了类成员注解
    private Integer age;

    @TestA(name = "构造方法",gid = Long.class,id = 2)//使用了构造方法注解
    public UserAnnotation(){

    }

    @TestA(name="public method",id=3,gid=Long.class) //类方法注解
    public void a(){
        @TestA(name="局部变量",id=4,gid=Long.class)//使用了局部变量注解
         Map m = new HashMap(0);
    }

    public void b(@TestA(name="参数",id=5,gid=Long.class) Integer a){ //使用了方法参数注解

    }
}
