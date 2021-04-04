package demo;

import java.util.concurrent.locks.ReentrantLock;

public class LockFairTest implements Runnable{
    private static ReentrantLock lock = new ReentrantLock(true);

    public void run() {
        while (true) {
            lock.lock();
            try {
                System.out.print(Thread.currentThread().getName() + "获得锁");
            } finally {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) {
        LockFairTest lft = new LockFairTest();
        Thread th1 = new Thread(lft,"A");
        Thread th2 = new Thread(lft,"B");
        th1.start();
        th2.start();
    }
}
