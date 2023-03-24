package com.wuxianggujun;

import java.util.function.Function;

/**
 * @author WuXiangGuJun
 * @create 2023-03-11 11:56
 **/
public abstract class Result<T> {

    private Result() {

    }

    public static <T> Result<T> ok(T value) {
        return new OK<>(value);
    }

    public static Result error(Throwable value) {
        return new ERROR(value);
    }

    public boolean OK() {
        return this instanceof OK;
    }

    public boolean ERROR() {
        return this instanceof ERROR;
    }

    public T unwrap() {
        if (OK()) {
            return this.get();
        } else {
            throw new RuntimeException((Throwable) this.get());
        }

    }

    public T unwrapElse(Function<Throwable, T> function) {
        if (!ERROR()) {
            return this.get();
        } else {
            return function.apply((Throwable) this.get());
        }

    }

    public abstract T get();

    public static class OK<T> extends Result<T> {

        private final T value;

        public OK(T value) {
            this.value = value;
        }

        @Override
        public T get() {
            return value;
        }
    }

    public static class ERROR extends Result<Throwable> {
        private final Throwable e;

        public ERROR(Throwable e) {
            this.e = e;
        }


        @Override
        public Throwable get() {
            return e;
        }
    }

}
