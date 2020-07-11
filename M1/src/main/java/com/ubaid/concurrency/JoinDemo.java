package com.ubaid.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JoinDemo {
    public static void main(String [] args) {
        Thread t1 = new Thread(new SendEmail(), "Email Sender");
        Thread t2 = new Thread(new SendNotification(), "Notification Sender");

        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main Method Ended");
    }
}

class SendEmail implements Runnable{

    @Override
    public void run() {
        IntStream.range(1, 10).forEach(n -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Sending Email");
            } catch (InterruptedException exp) {
                exp.printStackTrace();
            }
        });
    }
}

class SendNotification implements Runnable {
    @Override
    public void run() {
        IntStream.range(1, 10).forEach(n -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Sending Notification");
            } catch (InterruptedException exp) {
                exp.printStackTrace();
            }
        });
    }
}