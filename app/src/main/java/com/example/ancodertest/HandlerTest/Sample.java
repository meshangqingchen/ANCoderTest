package com.example.ancodertest.HandlerTest;

public class Sample {
    int s1 = 0;
    public static Sample mSample1 = new Sample();

    // 方法中的局部变量s2、mSample2存放在 栈内存
    // 变量mSample2所指向的对象实例存放在 堆内存
    // 该实例的成员变量s1、mSample1也存放在栈中
    public void method() {
        int s2 = 0;
        Sample mSample2 = new Sample();
    }

    public void test(){

        Sample a = mSample1;
        Sample aa = mSample1.mSample1;
        Sample aaa = mSample1.mSample1.mSample1;
    }
}
