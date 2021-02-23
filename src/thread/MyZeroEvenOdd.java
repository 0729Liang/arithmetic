package thread;

import java.util.function.IntConsumer;

public class MyZeroEvenOdd {

    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(12);
        IntConsumer printNumber = new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.print(value);
            }
        };
        Thread threadZero = new Thread(() -> {
            try {
                zeroEvenOdd.zero(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread threadOdd = new Thread(() -> {
            try {
                zeroEvenOdd.odd(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread threadEven = new Thread(() -> {
            try {
                zeroEvenOdd.even(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadZero.start();
        threadOdd.start();
        threadEven.start();
    }

    // 1.
    static class ZeroEvenOdd {

        private int n;
        private final Object mObject = new Object();
        private volatile int num = 0;
        private volatile boolean printZero = true;
        private volatile boolean printOdd = false;

        public ZeroEvenOdd(int n) {
            this.n = n;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            while (true) {
                synchronized (mObject) {
                    while (!printZero) {
                        mObject.wait();
                    }
                    printNumber.accept(0);
                    num++;
                    printZero = false;
                    printOdd = (num % 2 == 1);
                    mObject.notifyAll();
                    if (num >= n) {
                        break;
                    }
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            // 循环执行
            while (num <= n){
                synchronized (mObject) {
                    // 循环等待
                    while (printZero || printOdd) {
                        if (num >= n){
                            return;
                        }
                        mObject.wait();
                    }
                    printNumber.accept(num);
                    printZero = true;
                    mObject.notifyAll();
                }
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            // 注意需要循环等待
            while (num <= n){
                synchronized (mObject) {
                    // 循环等待
                    while (printZero) {
                        if (num >= n){
                            return;
                        }
                        mObject.wait();
                    }
                    printNumber.accept(num);
                    printZero = true;
                    mObject.notifyAll();
                }
            }
        }
    }
}
