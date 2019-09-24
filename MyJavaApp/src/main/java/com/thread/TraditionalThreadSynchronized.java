package com.thread;

public class TraditionalThreadSynchronized {
    public static void main(String[] args) {
        final OutPutter output = new OutPutter();
        new Thread() {
            public void run() {
                output.output("zhangsan_zhangsan");
            }
        }.start();
        new Thread() {
            public void run() {
                output.output("lisi_lisi");
            }
        }.start();
    }

    static class OutPutter {
        public void output(String name) {
            // TODO 为了保证对name的输出不是一个原子操作，这里逐个输出name的每个字符
            for (int i = 0; i < name.length(); i++) {
                System.out.print(name.charAt(i));

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}