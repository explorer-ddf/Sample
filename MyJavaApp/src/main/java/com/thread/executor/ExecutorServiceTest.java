package com.thread.executor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class ExecutorServiceTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        //这个方法有个问题，就是没有办法获知task的执行结果
        executorService.execute(new Runnable() {
            public void run() {
                System.out.println("Asynchronous task");
            }
        });

/////////////////////////////////
        Future future1 = executorService.submit(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Asynchronous task");
            }
        });

        //如果任务执行完成，future1.get()方法会返回一个null。注意，future1.get()方法会产生阻塞
        future1.get();  //returns null if the task has finished correctly.

/////////////////////////////////
        Future future2 = executorService.submit(new Callable(){
            public Object call() throws Exception {
                System.out.println("Asynchronous Callable");
                return "Callable Result";
            }
        });

        //如果任务执行完成，future.get()方法会返回Callable任务的执行结果。注意，future.get()方法会产生阻塞
        System.out.println("future2.get() = " + future2.get());

/////////////////////////////////
        Set<Callable<String>> callables = new HashSet<Callable<String>>();

        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 1";
            }
        });
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 2";
            }
        });
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 3";
            }
        });

        //invokeAny(...)方法接收的是一个Callable的集合，执行这个方法不会返回Future，但是会返回所有Callable任务中其中一个任务的执行结果。这个方法也无法保证返回的是哪个任务的执行结果，反正是其中的某一个。
        String result = executorService.invokeAny(callables);
        System.out.println("result = " + result);


        //invokeAll(...)与 invokeAny(...)类似也是接收一个Callable集合，但是前者执行之后会返回一个Future的List，其中对应着每个Callable任务执行后的Future对象。
        List<Future<String>> futures = executorService.invokeAll(callables);

        for(Future<String> future : futures){
            System.out.println("future.get = " + future.get());
        }

        //当我们使用完成ExecutorService之后应该关闭它，否则它里面的线程会一直处于运行状态。
        executorService.shutdown();
    }
}
