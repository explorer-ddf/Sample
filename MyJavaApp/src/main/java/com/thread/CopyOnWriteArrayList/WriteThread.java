package com.thread.CopyOnWriteArrayList;

import java.util.List;

public class WriteThread implements Runnable {
    private List<Integer> list;

    public WriteThread(List<Integer> list) {
        this.list = list;
        System.out.println("WriteThread:  hashcode =  " + list.hashCode());

    }

    @Override
    public void run() {
        this.list.add(10000);
        System.out.println("WriteThread: write data "+ 10000 + " hashcode =  " + list.hashCode());

    }
}