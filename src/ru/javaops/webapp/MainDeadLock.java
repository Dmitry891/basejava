package ru.javaops.webapp;

public class MainDeadLock {
    private static final String lock1 = "lock1";
    private static final String lock2 = "lock2";

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> printDeadLock(lock1, lock2)).start();
        new Thread(() -> printDeadLock(lock2, lock1)).start();
    }

    private static void printDeadLock(String lock1, String lock2) {
        String name = Thread.currentThread().getName();
        System.out.println(name + " thread is trying to capture " + lock1);
        synchronized (lock1) {
            System.out.println(name + " has captured the object " + lock1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " thread is trying to capture " + lock2);
            synchronized (lock2) {
                System.out.println(name + " has captured the object " + lock2);
            }
        }
    }
}
