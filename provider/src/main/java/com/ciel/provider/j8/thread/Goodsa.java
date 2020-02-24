package com.ciel.provider.j8.thread;

import java.util.concurrent.locks.ReentrantLock;

public class Goodsa {

    public volatile static int age = 1;

    public static void main(String[] args) throws InterruptedException {

        ReentrantLock lock = new ReentrantLock();

        new Thread(() -> age+=1).start();
        new Thread(() -> age+=1).start();
        new Thread(() -> age+=1).start();
        new Thread(() -> age+=1).start();

        while (true){
            Thread.sleep(2000);
            System.out.println(age);
        }

        // CopyOnWriteArrayList  适合 读多写少
        //ConcurrentHashMap  //适合少量并发 线程安全
        // ConcurrentSkipListMap //适合大量并发 线程安全
    }
}
