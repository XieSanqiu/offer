package singleton;

/**
 * 懒汉式——线程安全
 * 不推荐使用，阻塞时间太长
 */
public class Singleton3 {
    private static Singleton3 instance;
    private Singleton3(){}
    public static synchronized Singleton3 getInstance(){
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton3 instance = Singleton3.getInstance();
        System.out.println(instance);
    }
}
