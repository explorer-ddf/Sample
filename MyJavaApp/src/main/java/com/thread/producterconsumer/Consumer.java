package com.thread.producterconsumer;

public class Consumer implements Runnable {
    // 每次生产的产品数量
    private int num;

    // 所在放置的仓库
    private Storage storage;

    public Consumer(int num, Storage storage) {
        this.num = num;
        this.storage = storage;
    }

    @Override
    public void run() {
        storage.consume(num);
    }
}
