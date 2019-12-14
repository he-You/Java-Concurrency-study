package thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 简要说明:Java8+的并行计算
 * parallelStream提供了流的并行处理，它是Stream的另一重要特性，
 * 其底层使用Fork/Join框架实现。简单理解就是多线程异步任务的一种实现。
 * @author heyou
 * @date 2019-12-14 22:55
 */
public class CreateThreadSeven {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        list.stream().forEach(System.out::print);
        System.out.println();
        //并行流
        list.parallelStream().forEach(System.out::print);
    }
}
