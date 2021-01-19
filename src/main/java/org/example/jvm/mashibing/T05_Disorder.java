package org.example.jvm.mashibing;

/**
 * 测试乱序执行
 */
public class T05_Disorder {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (;;){
            i++;
            x = 0; y = 0;
            a = 0; b = 0;
            Thread one = new Thread(() -> {
                //shortWait(1000000);//等一等线程two再执行
                a = 1;
                x = b;
            });
            Thread other = new Thread(() -> {
                b = 1;
                y = a;
            });
            one.start();other.start();
            one.join();other.join();
            String result = "第" +i+"次（"+x+","+y+")";
            if (x == 0 && y == 0){
                System.err.println(result);
                break;
            }else {

            }
        }
    }

    public static void shortWait(long interval){
        long start = System.nanoTime();
        long end;
        do {
            end = System.nanoTime();
        }while (start + interval >= end);
    }
}
