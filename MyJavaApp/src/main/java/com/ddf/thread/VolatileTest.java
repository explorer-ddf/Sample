package com.ddf.thread;


/**
 * volatile关键字能保证可见性，但是不能保证原子性。可见性只能保证每次读取的是最新的值，但是volatile没办法保证对变量的操作的原子性。
 * <p>
 * 下面例子：自增操作是不具备原子性的，它包括读取变量的原始值、进行加1操作、写入工作内存。所以下面的例子无法保证线程安全
 * <p>
 * https://www.ibm.com/developerworks/cn/java/j-jtp06197.html
 */
public class VolatileTest {

    public static void main(String[] args) {
        final _InnerA test = new _InnerA();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        test.increase();
                    }
                }
            }.start();
        }

        while (Thread.activeCount() > 1) {
            Thread.yield();//保证前面的线程都执行完
        }
        System.out.println(test.inc);
    }

    static class _InnerA {
        public volatile int inc = 0;

        public void increase() {
            inc++;
        }
    }
}
