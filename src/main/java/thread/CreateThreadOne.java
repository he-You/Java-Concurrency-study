package thread;

/**
 * 简要说明:
 * 继承Thread类,重写run方法创建线程
 * 继承Thread类并重写run()方法，这种方式的弊端是一个类只能继承一个父类，如果这个类本身已经继承了其它类，就不能使用这种方式了。
 * @author heyou
 * @date 2019-12-13 23:26
 */
public class CreateThreadOne extends Thread{
    @Override
    public void run(){
        System.out.println(currentThread().getName()+" is running");
    }

    public static void main(String[] args) {
        new CreateThreadOne().run();
        new CreateThreadOne().start();
    }
}
