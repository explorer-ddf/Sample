package com.thread.producterconsumer;

import java.util.LinkedList;

public abstract class Storage {
    // 仓库最大存储量
    protected final int MAX_SIZE = 100;

    // 仓库存储的载体
    protected LinkedList<Object> list = new LinkedList<Object>();


    public abstract void produce(int num);

    public abstract void consume(int num);
}
