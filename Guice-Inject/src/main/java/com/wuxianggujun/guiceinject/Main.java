package com.wuxianggujun.guiceinject;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class Main {
    @Inject
    private Animal animal;

    public static void main(String[] args) {
        Main main = new Main();
        Injector injector = Guice.createInjector(new MainModule());
        //为当前实例注入容器内的对象
        injector.injectMembers(main);
        System.out.println(main.animal);
        System.out.println(injector.getInstance(Animal.class));
        main.animal.run();
    }
}


