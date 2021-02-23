package thread;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。请设计修改程序，以确保 "foobar" 被输出 n 次。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-foobar-alternately
 * <p>
 * BlockingQueue的使用: https://www.cnblogs.com/liuling/p/2013-8-20-01.html
 * CyclicBarrier:https://www.cnblogs.com/nullzx/p/5271964.html、https://www.cnblogs.com/brokencolor/p/9752941.html
 * ReentrantLock原理：https://blog.csdn.net/fuyuwei2015/article/details/83719444
 * Java并发控制：ReentrantLock Condition使用详解 https://www.cnblogs.com/java-chen-hao/p/10037209.html、https://www.cnblogs.com/hongdada/p/6150699.html、https://www.cnblogs.com/takumicx/p/9338983.html
 * Semaphore：https://blog.51cto.com/14977428/2545055、https://blog.csdn.net/hanchao5272/article/details/79780045
 *
 * 1.synchronized
 * 2.LinkedBlockingQueue
 * 3.CyclicBarrier
 * 4.ReentrantLock、Condition
 * 5.Semaphore
 */
public class AlternateFooBar {

    // 法1：synchronized
    class FooBar {
        private int n;
        private volatile boolean runFoo = true; // true 打印foo，false打印bar
        private final Object mObject = new Object();

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                synchronized (mObject) {
                    while (!runFoo) {
                        mObject.wait();
                    }
                    printFoo.run();
                    runFoo = false;// 通知可以打印bar
                    mObject.notifyAll();
                }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                synchronized (mObject) {
                    while (runFoo) {
                        mObject.wait();
                    }
                    printBar.run();
                    runFoo = true;// 通知可以打印foo
                    mObject.notifyAll();
                }
            }
        }
    }

    // 法2：LinkedBlockingQueue
    class FooBar2 {
        private int n;


        public FooBar2(int n) {
            this.n = n;
        }

        private LinkedBlockingQueue<Integer> fooQueue = new LinkedBlockingQueue<>(1);
        private LinkedBlockingQueue<Integer> barQueue = new LinkedBlockingQueue<>(1);

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                fooQueue.put(1);// 阻塞式向链表中添加一个数
                printFoo.run();
                barQueue.put(1);
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                barQueue.take();// 阻塞式向链表中取出一个数
                printBar.run();
                fooQueue.take();
            }
        }
    }

    // 法3：CyclicBarrier
    class FooBar3 {
        private int n;


        public FooBar3(int n) {
            this.n = n;
        }


        private volatile boolean runFoo = true;
        private CyclicBarrier mCyclicBarrier = new CyclicBarrier(2);

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                if (runFoo) {
                    printFoo.run();
                    runFoo = false;
                }
                try {
                    mCyclicBarrier.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                if (!runFoo) {
                    printBar.run();
                    runFoo = true;
                }
                try {
                    mCyclicBarrier.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    // 法4：ReentrantLock、Condition
    class FooBar4 {
        private int n;


        public FooBar4(int n) {
            this.n = n;
        }


        private volatile boolean runFoo = true;
        private ReentrantLock mLock = new ReentrantLock();
        private Condition fooCondition = mLock.newCondition();
        private Condition barCondition = mLock.newCondition();

        public void foo(Runnable printFoo) throws InterruptedException{

            for (int i = 0; i < n; i++) {
                try {
                    mLock.lock();
                    if (!runFoo) {
                        fooCondition.await();
                    }
                    printFoo.run();
                    runFoo = false;
                    barCondition.signal();
                } catch (Exception e) {

                } finally {
                    mLock.unlock();
                }

            }
        }

        public void bar(Runnable printBar) throws InterruptedException{

            for (int i = 0; i < n; i++) {
                try {
                    mLock.lock();
                    if (runFoo) {
                        barCondition.await();
                    }
                    printBar.run();
                    runFoo = true;
                    fooCondition.signal();
                } catch (Exception e) {

                } finally {
                    mLock.unlock();
                }
            }
        }
    }

    // 法5：Semaphore
    class FooBar5 {
        private int n;


        public FooBar5(int n) {
            this.n = n;
        }


        private Semaphore fooSemaphore = new Semaphore(1);
        private Semaphore barSemaphore = new Semaphore(0);

        public void foo(Runnable printFoo) throws InterruptedException{

            for (int i = 0; i < n; i++) {
                fooSemaphore.acquire();
                printFoo.run();
                barSemaphore.release();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException{

            for (int i = 0; i < n; i++) {
                barSemaphore.acquire();
                printBar.run();
                fooSemaphore.release();
            }
        }
    }

}
