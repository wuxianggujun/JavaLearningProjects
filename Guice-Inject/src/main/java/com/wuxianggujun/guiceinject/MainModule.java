package com.wuxianggujun.guiceinject;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class MainModule extends AbstractModule{

    @Override
    protected void configure() {
        //绑定时指定为单利
        bind(Animal.class).to(Dog.class).in(Singleton.class);
    }
}
