package lock;

import sun.misc.Unsafe;

/**
 * 简要说明:
 * 基于CAS实现自定义自旋锁
 * @author heyou
 * @date 2019-12-08 23:27
 */
public class LockBaseOnCas {
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static long stateOffset;
    /**
     * 简单的自旋
     */
    //用于标记当前线程是否获取到锁
    volatile int status = 0;
    public void simpleLock(){
        //若当前请求资源已被其他线程加锁,则处于死循环状态,每次循环堆资源进行是否加锁判断,当资源被释放锁后,跳出该循环,当前线程获取锁
        while(!compareAndSetState(0,1)){
            
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
}
