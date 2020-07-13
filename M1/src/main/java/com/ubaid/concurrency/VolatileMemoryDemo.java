package com.ubaid.concurrency;

import java.util.concurrent.TimeUnit;

public class VolatileMemoryDemo {

    private static volatile boolean stop = false;

    private static synchronized void requestStop() {
        stop = true;
    }

    private static synchronized boolean isStop() {
        return stop;
    }

    public static void main(String [] args) throws InterruptedException {

        new Thread(() -> {
            while (!stop) {
                System.out.print(".");
            }
            System.out.println();
        }).start();

        TimeUnit.MILLISECONDS.sleep(1);
        requestStop();
    }
}
