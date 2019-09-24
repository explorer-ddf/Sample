package com.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 用sychronized修饰的方法或者语句块在代码执行完之后锁自动释放，而用Lock需要我们手动释放锁，所以为了保证锁最终被释放(发生异常情况)，要把互斥区放在try内，释放锁放在finally内。
 *
 * http://blog.csdn.net/ghsau/article/details/7461369
 */
public class LockTest {
    public static void main(String[] args) {
        final Outputter1 output = new Outputter1();
        new Thread() {
            public void run() {
                output.output("zhangsan");
            }

            ;
        }.start();
        new Thread() {
            public void run() {
                output.output("lisi");
            }

            ;
        }.start();
    }


    static class Outputter1 {
        private Lock lock = new ReentrantLock();// 锁对象

        public void output(String name) {
            // TODO 线程输出方法
            lock.lock();// 得到锁
            try {
                for (int i = 0; i < name.length(); i++) {
                    System.out.print(name.charAt(i));

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                lock.unlock();// 释放锁
            }
        }
    }
}
