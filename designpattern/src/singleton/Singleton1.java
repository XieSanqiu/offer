package singleton;

/**
 * 懒汉式——线程不安全
 */
public class Singleton1 {
    private static Singleton1 instance;
    private Singleton1(){}

    public static Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton1 instance = Singleton1.getInstance();
        System.out.println(instance);
    }
}
