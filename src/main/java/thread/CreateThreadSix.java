package thread;

import java.util.concurrent.*;

/**
 * 简要说明:通过线程池创建线程
 * 使用线程池的方式，可以复用线程，节约系统资源。
 * @author heyou
 * @date 2019-12-14 00:28
 */
public class CreateThreadSix {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i=0;i<100;i++){
            threadPool.execute(()-> System.out.println(Thread.currentThread().getName()+" is running"));
        }
    }
}
