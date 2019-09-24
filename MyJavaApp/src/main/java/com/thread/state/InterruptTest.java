package com.thread.state;

/**
 * interrupt()方法并不是中断线程的执行，而是为调用该方法的线程对象打上一个标记，设置其中断状态为true，通过isInterrupted()方法可以得到这个线程状态
 * <p>
 * http://blog.csdn.net/ghsau/article/details/17560467
 */
public class InterruptTest {
    public static void main(String[] args) {
        MyThread t = new MyThread("MyThread");
        t.start();
        try {
            Thread.sleep(100);// 睡眠100毫秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.interrupt();// 中断t线程
    }


    static class MyThread extends Thread {
        int i = 0;

        public MyThread(String name) {
            super(name);
        }

        public void run() {
//        while (!isInterrupted())
            while (true) {// 运行后，我们发现，线程t一直在执行，没有被中断
                System.out.println(getName() + getId() + "执行了" + ++i + "次");
            }
        }
    }
}