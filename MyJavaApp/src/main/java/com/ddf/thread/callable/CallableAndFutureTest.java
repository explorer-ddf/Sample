package com.ddf.thread.callable;

import java.util.Random;
import java.util.concurrent.*;

public class CallableAndFutureTest {
    public static void main(String[] args) {
//        Callable<Integer> callable = new Callable<Integer>() {
//            public Integer call() throws Exception {
//                return new Random().nextInt(100);
//            }
//        };
//
//        FutureTask<Integer> future = new FutureTask<Integer>(callable);
//        new Thread(future).start();
//        try {
//            Thread.sleep(5000);// 可能做一些事情
//            System.out.println(future.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }


//        另一种方式使用Callable和Future，通过ExecutorService的submit方法执行Callable，并返回Future
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<Integer> future = threadPool.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        });
        try {
            Thread.sleep(5000);// 可能做一些事情
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
