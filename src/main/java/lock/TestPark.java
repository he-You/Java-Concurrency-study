package lock;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 简要说明:park
 *
 * @author heyou
 * @date 2019-12-10 21:03
 */
public class TestPark {
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
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main-------1 ");
        //叫醒已经park的 线程
        LockSupport.unpark(t1);
    }

    public static void testSysn(){
        System.out.println("t1 ---1");
        //park()方法是由UNSAFE提供,LockSupport只是对该方法进行了封装
        LockSupport.park();
        System.out.println("t1----2");

    }
}
