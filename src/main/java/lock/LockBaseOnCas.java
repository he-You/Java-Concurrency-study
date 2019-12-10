package lock;

import sun.misc.Unsafe;

import java.util.Queue;
import java.util.concurrent.locks.LockSupport;

import static java.lang.Thread.*;

/**
 * 简要说明:
 * 基于CAS实现自定义自旋锁
 * CAS操作:原子性的比较赋值
 * @author heyou
 * @date 2019-12-08 23:27
 */
public class LockBaseOnCas {
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static long stateOffset;
    /**
     * 简单的自旋:
     * 缺点:占用cpu资源:没有竞争到锁的资源会一直占用cpu资源进行cas操作
     */
    //用于标记当前线程是否获取到锁
    volatile int status = 0;
    //若当前请求资源已被其他线程加锁,则处于死循环状态,每次循环堆资源进行是否加锁判断,当资源被释放锁后,跳出该循环,当前线程获取锁
    public void simpleLock(){

        while(!compareAndSetState(0,1)){

        }
    }

    /**
     * yeild+自旋:让得不到锁的线程主动让出cpu的资源,而不是空转等待,当线程竞争锁失败时,会调用yield方法让出cpu资源,但是该方法只适用于
     * 两个线程,因为在多线程环境下,由CPU调度yield()无法保证下次请求锁的线程是上一次请求的线程
     */
    public void yieldLock(){
        //若当前请求资源已被其他线程加锁,则处于死循环状态,每次循环堆资源进行是否加锁判断,当资源被释放锁后,跳出该循环,当前线程获取锁
        while(!compareAndSetState(0,1)){
            yield();
        }
    }

    /**
     *  sleep+自旋:主要是睡眠时间不确定,浪费性能
     */
    public void sleepLock(){
        //若当前请求资源已被其他线程加锁,则处于死循环状态,每次循环堆资源进行是否加锁判断,当资源被释放锁后,跳出该循环,当前线程获取锁
        while(!compareAndSetState(0,1)){
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * park+自旋:
     */
    Queue parkQueue;
    public void parkLock(){

        //若当前请求资源已被其他线程加锁,则处于死循环状态,每次循环堆资源进行是否加锁判断,当资源被释放锁后,跳出该循环,当前线程获取锁
        while(!compareAndSetState(0,1)){
            park();
        }

    }

    /**
     * cas操作,修改当前status状态,成功返回true不成功返回false
     * @param expect
     * @param newValue
     * @return
     */
    private boolean compareAndSetState(int expect, int newValue) {
        return unsafe.compareAndSwapInt(this, stateOffset, expect, newValue);
    }
    //开锁
    public void unlock(){
        status=0;
    }

    /**
     * park方法
     */
    public void park(){
        //将未取得锁的线程放入队列
        parkQueue.add(currentThread());
        //将当前cpu释放,当线程执行到park(),while循环就不再循环下去,直到该线程再次被唤醒
        LockSupport.park();
    }

    public void unPark(){
        //获取要唤醒的队列头部线程
        Thread t = (Thread) parkQueue.peek();
        LockSupport.unpark(t);
    }
}
