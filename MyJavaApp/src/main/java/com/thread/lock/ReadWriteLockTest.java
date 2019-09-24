package com.thread.lock;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * http://blog.csdn.net/ghsau/article/details/7461369
 * <p>
 * 要实现写入和写入互斥，读取和写入互斥，读取和读取互斥，在set和get方法加入sychronized修饰符
 * public synchronized void set(int data) {...}
 * public synchronized void get() {...}
 * <p>
 * 虽然写入和写入互斥了，读取和写入也互斥了，但是读取和读取之间也互斥了，不能并发执行，效率较低
 * <p>
 * 读和写是互斥的，写和写是互斥的，但是读和读是不需要互斥的:ReadWriteLock
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        final Data data = new Data();
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        data.set(new Random().nextInt(30));
                    }
                }
            }).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        data.get();
                    }
                }
            }).start();
        }
    }

    static class Data {
        private int data;// 共享数据
        private ReadWriteLock rwl = new ReentrantReadWriteLock();

        public void set(int data) {
            rwl.writeLock().lock();// 取到写锁
            try {
                System.out.println(Thread.currentThread().getName() + "准备写入数据");
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.data = data;
                System.out.println(Thread.currentThread().getName() + "写入" + this.data);
            } finally {
                rwl.writeLock().unlock();// 释放写锁
            }
        }

        public void get() {
            rwl.readLock().lock();// 取到读锁
            try {
                System.out.println(Thread.currentThread().getName() + "准备读取数据");
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "读取" + this.data);
            } finally {
                rwl.readLock().unlock();// 释放读锁
            }
        }
    }
}
