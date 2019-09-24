package com.ddf.thread.CopyOnWriteArrayList;

import java.util.List;

public class ReadThread implements Runnable {
    private List<Integer> list;

    public ReadThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("ReadThread: read data " + list.get(i));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

//        for (int i : list) {
//            System.out.println("ReadThread: read data " + list.get(i));
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}