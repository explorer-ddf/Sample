package com.thread.producterconsumer;

public class Main {
    public static void main(String[] args) {
//        Storage storage = new StorageImplByWaitNotify();
        Storage storage = new StorageImplByAwaitSignal();
//        Storage storage = new StorageImplByBlockingQueue();

        for (int i = 0; i < 20; i++) {
            new Thread(new Producer(10, storage)).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(new Consumer(20, storage)).start();
        }

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

