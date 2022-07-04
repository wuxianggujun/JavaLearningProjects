package com.wuxianggujun.guiceinject;

import com.google.inject.Singleton;

@Singleton
public class Dog implements Animal{
    @Override
    public void run() {
        System.out.println("Dog run...");
    }
}
