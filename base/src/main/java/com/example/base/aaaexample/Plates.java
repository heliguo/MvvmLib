package com.example.base.aaaexample;

/**
 * author:lgh on 2020/6/13 17:35
 */
public class Plates<T extends Fruit> {

    private T t;

    public Plates(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
