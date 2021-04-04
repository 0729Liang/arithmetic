package core.thread;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：
 * 三个不同的线程将会共用一个 Foo 实例。
 *
 *     线程 A 将会调用 first() 方法
 *     线程 B 将会调用 second() 方法
 *     线程 C 将会调用 third() 方法
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-in-order
 * 1.synchronized+volatile
 * 2.LinkedBlockingQueue
 * 3.CyclicBarrier
 * 4.ReentrantLock、Condition
 * 5.Semaphore
 */
public class OrderPrint {


    // 法1：synchronized
    class Foo1 {
        private volatile boolean runSecond = false;
        private volatile boolean runThird = false;
        private Object mObject = new Object();

        public void first(Runnable printFirst) throws InterruptedException {

            synchronized (mObject) {
                while (runSecond || runThird) {
                    mObject.wait();
                }
                printFirst.run();
                runSecond = true;
                runThird = false;
                mObject.notifyAll();
            }
        }

        public void second(Runnable printSecond) throws InterruptedException {
            synchronized (mObject) {
                while (!runSecond) {
                    mObject.wait();
                }
                printSecond.run();
                runSecond = false;
                runThird = true;
                mObject.notifyAll();
            }
        }

        public void third(Runnable printThird) throws InterruptedException {

            synchronized (mObject) {
                while (!runThird) {
                    mObject.wait();
                }
                printThird.run();
                runSecond = false;
                runThird = false;
                mObject.notifyAll();
            }
        }
    }

    // 法2：LinkedBlockingQueue
    class Foo2 {

        LinkedBlockingQueue<Integer> queueSecond = new LinkedBlockingQueue<>(1);
        LinkedBlockingQueue<Integer> queueThird = new LinkedBlockingQueue<>(1);


        public void first(Runnable printFirst) throws InterruptedException {
            printFirst.run();
            queueSecond.put(1);
        }

        public void second(Runnable printSecond) throws InterruptedException {
            queueSecond.take();
            printSecond.run();
            queueThird.put(1);
        }

        public void third(Runnable printThird) throws InterruptedException {
            queueThird.take();
            printThird.run();
        }
    }

    // 法3：CyclicBarrier 无法接待，适用于循环执行的线程
    class Foo3 {

        private volatile boolean runSecond = false;
        private volatile boolean runThird = false;
        private CyclicBarrier mCyclicBarrier = new CyclicBarrier(3);

        public void first(Runnable printFirst) throws InterruptedException {
            if (!runSecond && !runThird) {
                printFirst.run();
                runSecond = true;
                runThird = false;
            }
            try {
                mCyclicBarrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        public void second(Runnable printSecond) throws InterruptedException {
            if (runSecond) {
                printSecond.run();
                runSecond = false;
                runThird = true;
            }
            try {
                mCyclicBarrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        public void third(Runnable printThird) throws InterruptedException {
            if (runThird) {
                printThird.run();
                runSecond = false;
                runThird = false;
            }
            try {
                mCyclicBarrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    // 法4：ReentrantLock、Condition
    class Foo4 {

        private ReentrantLock mReentrantLock = new ReentrantLock();
        private Condition mCondition = mReentrantLock.newCondition();
        private volatile boolean runSecond = false;
        private volatile boolean runThird = false;

        public void first(Runnable printFirst) throws InterruptedException {
             try {
                 mReentrantLock.lock();
                 while (runSecond || runThird) {
                     mCondition.await();
                 }
                 printFirst.run();
                 runSecond = true;
                 runThird = false;
                 mCondition.signalAll();
             }catch (Exception e){

             }finally {
                 mReentrantLock.unlock();
             }
        }

        public void second(Runnable printSecond) throws InterruptedException {
            try {
                mReentrantLock.lock();
                while (!runSecond){
                    mCondition.await();
                }
                printSecond.run();
                runSecond = false;
                runThird = true;
                mCondition.signalAll();
            }catch (Exception e){

            }finally {
                mReentrantLock.unlock();
            }
        }

        public void third(Runnable printThird) throws InterruptedException {
            try {
                mReentrantLock.lock();
                while (!runThird){
                    mCondition.await();;
                }
                runSecond = false;
                runThird = false;
                printThird.run();
            }catch (Exception e){

            }finally {
                mReentrantLock.unlock();
            }
        }
    }

    // 法5：Semaphore
    class Foo5 {

        private Semaphore semaphoreSecond = new Semaphore(0);
        private Semaphore semaphorThird = new Semaphore(0);

        public void first(Runnable printFirst) throws InterruptedException {
            printFirst.run();
            semaphoreSecond.release();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            semaphoreSecond.acquire();
            printSecond.run();
            semaphorThird.release();
        }

        public void third(Runnable printThird) throws InterruptedException {
            semaphorThird.acquire();
            printThird.run();
        }
    }

    class Foo6{
        private AtomicInteger firstJobDone = new AtomicInteger(0);
        private AtomicInteger secondJobDone = new AtomicInteger(0);


        public void first(Runnable printFirst) throws InterruptedException {
            printFirst.run();
            firstJobDone.incrementAndGet();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            while (firstJobDone.get() != 1) {
            }
            printSecond.run();
            secondJobDone.incrementAndGet();
        }

        public void third(Runnable printThird) throws InterruptedException {
            while (secondJobDone.get() != 1) {
            }
            printThird.run();
        }

    }
}
