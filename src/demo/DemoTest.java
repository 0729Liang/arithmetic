package demo;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DemoTest {
    public static String AAA = "AAA";
    private Thread mThread1;
    private Thread mThread2;

    public static void main(String[] args) {
        DemoTest demoTest = new DemoTest();
        for (int i = 0; i < 10; i++) {
            demoTest.backupDb(i);
        }

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            demoTest.backupDb2(i);
            try {
                Thread.sleep(100);
            }catch (Exception e){

            }
        }
    }


    public  volatile boolean isBackUpping = false;
    public  ReentrantLock sReentrantLock = new ReentrantLock();
    public  Condition sCondition = sReentrantLock.newCondition();

    private void backupDb(int tag) {
        mThread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sReentrantLock.lock();
                    if (isBackUpping) {
                        sCondition.await();
                    }
                    isBackUpping = true;
                    int millis = (int) (new Random().nextFloat() * 1000);
                    System.out.println("sleep:" + millis + " num:" + tag);
                    Thread.sleep(millis);
                    isBackUpping = false;
                    sCondition.signal();
                } catch (Exception e) {

                } finally {
                    sReentrantLock.unlock();
                }
            }
        }, "s-rbn-bg-0");
        mThread1.start();
    }

    private void backupDb2(int tag) {
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        sReentrantLock.lock();
                        isBackUpping = true;
                        int millis = (int) (new Random().nextFloat() * 1000);
                        System.out.println("---------------------sleep:" + millis + " num:" + tag);
                    } catch (Exception e) {

                    } finally {
                        sReentrantLock.unlock();
                    }
                }
            }, "s-rbn-bg-0").start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
