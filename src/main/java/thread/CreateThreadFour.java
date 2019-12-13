package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 简要说明:实现CallAble接口
 * 实现Callabe接口，可以获取线程执行的结果，FutureTask实际上实现了Runnable接口。
 * @author heyou
 * @date 2019-12-13 23:47
 */
public class CreateThreadFour implements Callable {
    @Override
    public Object call() throws Exception {
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getId() + " is running");
        return Thread.currentThread().getId();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Long> task = new FutureTask<>(new CreateThreadFour());
        new Thread(task).start();
        System.out.println("等待完成任务");
        Long result = task.get();
        System.out.println("任务结果：" + result);
    }
}
