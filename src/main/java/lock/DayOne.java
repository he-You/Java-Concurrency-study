package lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 简要说明:
 * reentrantlock
 * @author heyou
 * @date 2019-12-06 22:50
 */
public class DayOne {
    private static ReentrantLock reentrantLock = new ReentrantLock();
    public static void main(String[] args) {
        System.out.println();
        Thread t1 = new Thread(){
            @Override
            public void run() {
                testSysn();
            }
        };
        t1.setName("t1");
        Thread t2 = new Thread(){
            @Override
            public void run() {
                testSysn();
            }
        };
        t2.setName("t2");

        t1.start();
        t2.start();
    }

    public static void testSysn(){
        reentrantLock.lock();
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }
}
