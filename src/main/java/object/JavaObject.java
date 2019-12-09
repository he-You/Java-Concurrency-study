package object;

import org.openjdk.jol.info.ClassLayout;

/**
 * 简要说明:
 * Java对象布局
 * @author heyou
 * @date 2019-12-09 12:55
 */
public class JavaObject {
    static  TargetClass targetClass = new TargetClass();
    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(targetClass).toPrintable());
    }
}
