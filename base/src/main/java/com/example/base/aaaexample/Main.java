package com.example.base.aaaexample;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:lgh on 2020/6/12 16:39
 * 虚引用的作用：当虚引用管理的对象被回收时，与之关联的队列不为空
 */
class Main {
    private static final ReferenceQueue<M> QUEUE = new ReferenceQueue<>();
    private static final List<Byte[]>      LIST  = new ArrayList<>();

    public static void main(String[] args) {

        PhantomReference<M> phantomReference = new PhantomReference<>(new M(), QUEUE);

        new Thread(() -> {
            while (true) {
                LIST.add(new Byte[1024 * 1024]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                System.out.println(phantomReference.get());
            }
        }).start();

        new Thread(() -> {
            while (true) {
                Reference<? extends M> poll = QUEUE.poll();
                if (poll != null) {
                    System.out.println("----虚引用对象被回收了-----" + poll);
                }
            }
        }).start();
    }

    static class M {

    }

    static List<? extends Fruit> extendsList = new ArrayList<>();

    static List<? super Fruit> superList = new ArrayList<>();

    /**
     * 通配符<?>和<T>的区别在于，对编译器来说所有的T都代表同一种类型
     * <?>没有这种约束
     * <p>
     * PECS（Producer Extends Consumer Super）原则
     * 频繁往外读取内容的，适合用上界Extends。取出的类为 extends 的类
     * 经常往里插入的，适合用下界Super。存放粒度小的类，取出为object类
     * </p>
     */
    static void test() {
        //        extendsList.add(new Apple());// compile error
        //        extendsList.add(new Eat());// compile error
        //        extendsList.add(new Fruit());// compile error
        //        extendsList.add(new Eat());// compile error
        Fruit fruit = extendsList.get(0);
        superList.add(new Apple());
        //        superList.add(new Eat());// compile error
        Apple object = ((Apple) superList.get(0));//需要强转，只能得到object类

        Plates<Fruit> plates = new Plates<>(new Fruit());

    }

}
