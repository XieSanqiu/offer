package singleton;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 双重校验锁——线程安全
 */
public class Singleton4 {
    //volatile防止重排序
    private static volatile Singleton4 instance;
    private Singleton4(){}
    public static Singleton4 getInstance() {
        if (instance == null) {
            synchronized (Singleton4.class) {
                if (instance == null) {
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton4 instance = Singleton4.getInstance();
        System.out.println(instance);
    }
}
