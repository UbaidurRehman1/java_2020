package com.ubaid.concurrency;

import java.util.concurrent.TimeUnit;

public class FirstThreadDemo {
    public static void main(String [] args) {
        Task task = new Task();
        Thread thread = new Thread(task);
        thread.start();
        try {
//            Thread.sleep(3000);
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Inside Main");
    }
}

/*Good approach*/
class Task implements Runnable {

    public Task() {
        System.out.println("Inside the constructor of Task");
    }

    @Override
    public void run() {
        System.out.println("Inside Run");
        go();
    }

    private void go() {
        System.out.println("Inside go");
        more();
    }

    private void more() {
        System.out.println("Inside more");
    }
}

/*Not a good approach*/
class Task2 extends Thread {
    public Task2() {
        System.out.println("Inside the constructor of Task2");
    }

    public void run() {
        System.out.println("Inside Run");
        go();
    }

    private void go() {
        System.out.println("Inside go");
        more();
    }

    private void more() {
        System.out.println("Inside more");
    }

}
