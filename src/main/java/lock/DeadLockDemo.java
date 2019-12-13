package lock;

/**
 * 简要说明:
 * 线程死锁
 * 线程 A 通过 synchronized (resource1) 获得 resource1 的监视器锁，
 * 然后通过 Thread.sleep(1000);让线程 A 休眠 1s 为的是让线程 B 得到执行
 * 然后获取到 resource2 的监视器锁。线程 A 和线程 B 休眠结束了都开始企图请求获取对方的资源，
 * 然后这两个线程就会陷入互相等待的状态，这也就产生了死锁
 * @author heyou
 * @date 2019-12-13 13:48
 */
public class DeadLockDemo {
    //资源 1
    private static Object resource1 = new Object();
    //资源 2
    private static Object resource2 = new Object();

    public static void main(String[] args) {
            new Thread(() -> {
                synchronized (resource1) {
                    System.out.println(Thread.currentThread() + "get resource1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread() + "waiting get resource2");
                    synchronized (resource2) {
                        System.out.println(Thread.currentThread() + "get resource2");
                    }
                }
            }, "线程 1").start();
            new Thread(() -> {
                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + "get resource2");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread() + "waiting get resource1");
                    synchronized (resource1) {
                        System.out.println(Thread.currentThread() + "get resource1");
                    }
                }
            }, "线程 2").start();
        }

}
