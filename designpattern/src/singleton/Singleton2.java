package singleton;

/**
 * 饿汉式——线程安全
 * 失去了延迟实例化带来的节约资源的好处
 */
public class Singleton2 {
    private static Singleton2 instance = new Singleton2();
    private Singleton2(){ }
    public static Singleton2 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        Singleton2 instance = Singleton2.getInstance();
        System.out.println(instance);
    }
}
