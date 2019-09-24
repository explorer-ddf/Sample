package com.thread.producterconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * （2）await() / signal()方法
 *
 * 为什么需要使用condition呢？简单一句话，lock更灵活。以前的方式只能有一个等待队列，在实际应用时可能需要多个，比如读和写。
 * 为了这个灵活性，lock将同步互斥控制和等待队列分离开来，互斥保证在某个时刻只有一个线程访问临界区（lock自己完成），等待队列
 * 负责保存被阻塞的线程（condition完成）。
 *
 * 通过查看ReentrantLock的源代码发现，condition其实是等待队列的一个管理者，condition确保阻塞的对象按顺序被唤醒。
 *
 * 多个Condition的强大之处:假设缓存队列中已经存满，那么阻塞的肯定是写线程，唤醒的肯定是读线程，相反，阻塞的肯定是读线程，唤醒的肯定是写线程，
 * 那么假设只有一个Condition会有什么效果呢，缓存队列中已经存满，这个Lock不知道唤醒的是读线程还是写线程了，如果唤醒的是读线程，皆大欢喜，如果
 * 唤醒的是写线程，那么线程刚被唤醒，又被阻塞了，这时又去唤醒，这样就浪费了很多时间。
 */
public class StorageImplByAwaitSignal extends Storage {

    // 锁
    private final Lock lock = new ReentrantLock();

    // 仓库满的条件变量
    private final Condition full = lock.newCondition();

    // 仓库空的条件变量
    private final Condition empty = lock.newCondition();

    @Override
    public void produce(int num) {
        // 获得锁
        lock.lock();

        // 如果仓库剩余容量不足
        while (list.size() + num > MAX_SIZE) {
            System.out.println("【要生产的产品数量】:" + num + "/t【库存量】:" + list.size() + "/t暂时不能执行生产任务!");
            try {
                //使当前线程加入 await() 等待队列中，并释放当锁，当其他线程调用signal()会重新请求锁
                full.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 生产条件满足情况下，生产num个产品
        for (int i = 1; i <= num; ++i) {
            list.add(new Object());
        }

        System.out.println("【已经生产产品数】:" + num + "/t【现仓储量为】:" + list.size());

        //唤醒 await()等待队列中所有的线程。
        full.signalAll();
        empty.signalAll();

        // 释放锁
        lock.unlock();
    }

    @Override
    public void consume(int num) {
        // 获得锁
        lock.lock();

        // 如果仓库存储量不足
        while (list.size() < num) {
            System.out.println("【要消费的产品数量】:" + num + "/t【库存量】:" + list.size() + "/t暂时不能执行生产任务!");
            try {
                // 由于条件不满足，消费阻塞
                empty.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 消费条件满足情况下，消费num个产品
        for (int i = 1; i <= num; ++i) {
            list.remove();
        }

        System.out.println("【已经消费产品数】:" + num + "/t【现仓储量为】:" + list.size());

        // 唤醒其他所有线程
        full.signalAll();
        empty.signalAll();

        // 释放锁
        lock.unlock();
    }
}
