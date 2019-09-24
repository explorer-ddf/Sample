package com.ddf.thread.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 以确定的时间间隔或在特定时间执行给定的任务。这就是 ScheduledExecutorService 的应用范围，它扩展了 ExecutorService。
 *
 * 创建一个每隔 1 秒跳一次的 “心跳” 命令，使用 ScheduledExecutorService 可以轻松实现
 */
public class ScheduledExecutorServiceTest {

    public static void main(String[] args) {
        ScheduledExecutorService ses =
                Executors.newScheduledThreadPool(1);
        Runnable pinger = new Runnable() {
            public void run() {
                System.out.println("PING!");
            }
        };
        ses.scheduleAtFixedRate(pinger, 1, 1, TimeUnit.SECONDS);
    }
}
