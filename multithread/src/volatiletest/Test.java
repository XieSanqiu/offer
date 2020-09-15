package volatiletest;

public class Test {
    // 用于控制线程当前的执行状态(有没有volatile好像没啥区别)
    private static volatile boolean running = false;

    // 开始执行
    public static void start(){
        running = true;
    }

    private static void doSomething(){
        System.out.println("do something...");
    }

    public static void main(String[] args) {
        // 开启一条线程
        Thread thread = new Thread(new Runnable(){
            public void run(){
                // 开关
                while(!running){
                    try {
                        System.out.println("sleep...");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 执行线程任务
                doSomething();
            }
        });
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        start();
    }
}
