package com.example.base;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

/**
 * author:lgh on 2020/6/12 16:39
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
}
