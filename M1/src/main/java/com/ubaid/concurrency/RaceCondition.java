package com.ubaid.concurrency;

public class RaceCondition {
    public static void main(String [] args) {
        BankAccount task = new BankAccount();
        task.setBalance(100);

        Thread ubaid = new Thread(task, "Ubaid");
        Thread attiq = new Thread(task, "Attiq");

        attiq.start();
        ubaid.start();

    }
}

class BankAccount implements Runnable {
    int balance = 100;

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public void run() {
        makeWithdrawal(75);
        if (balance < 0) {
            System.out.println("Money overdrawn");
        }
    }

    private synchronized void makeWithdrawal(int amount) {
        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " is about to withdraw");
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " has withdrawn " + amount);
        } else {
            System.out.println("Sorry not enough balance for " + Thread.currentThread().getName());
        }
    }
}
