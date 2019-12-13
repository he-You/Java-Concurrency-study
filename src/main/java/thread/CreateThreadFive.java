package thread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 简要说明:定时器
 *
 * @author heyou
 * @date 2019-12-13 23:50
 */
public class CreateThreadFive {
    public static void main(String[] args) {
        Timer timer = new Timer();
        // 每隔1秒执行一次
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " is running");
            }
        }, 0 , 1000);
    }
}
