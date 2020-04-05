package com.ciel.provider.j8.thread;

import lombok.SneakyThrows;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Goodsa {

    public volatile static int age = 1;

    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor threadPool =
                new ThreadPoolExecutor(20, 30, 5, TimeUnit.SECONDS,
                        new ArrayBlockingQueue<>(30));

        threadPool.execute(() -> System.out.println("aaa")); //会直接抛出任务执行时的异常

        threadPool.submit(() -> System.out.println("bbb")); //submit会吃掉异常


        LongAdder longAdder = new LongAdder();

        AtomicLong atomicLong = new AtomicLong();

        Lock lock = new ReentrantLock();

        ReadWriteLock lock2 = new ReentrantReadWriteLock();


        for (int i = 0; i < 100000; i++) {

            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    lock.lock();
                    Thread.sleep(10);
                    longAdder.add(1);
                    System.out.println("longAdder:" + longAdder);

                    long l = atomicLong.incrementAndGet();

                    System.out.println("AtomicLong:" + l);

                    lock.unlock();

                }
            }).start();
        }

        // CopyOnWriteArrayList  适合 读多写少
        //ConcurrentHashMap  //适合少量并发 线程安全
        // ConcurrentSkipListMap //适合大量并发 线程安全


    }
}
