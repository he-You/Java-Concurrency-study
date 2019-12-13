package thread;

/**
 * 简要说明:
 * 实现Runnable接口
 * 这种方式的好处是一个类可以实现多个接口，不影响其继承体系。
 * @author heyou
 * @date 2019-12-13 23:26
 */
public class CreateThreadTwo implements Runnable{
    public static void main(String[] args) {
        new Thread(new CreateThreadTwo()).start();
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running");
    }
}
