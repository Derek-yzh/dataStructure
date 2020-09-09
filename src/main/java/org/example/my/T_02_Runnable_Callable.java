package org.example.my;

import java.util.concurrent.TimeUnit;

/**
 * @Author Derek
 * 模拟双接口功能实现
 * 2020-09-04 09:58:23
 */
public class T_02_Runnable_Callable{

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new T_R_C());
        thread.start();
        thread.join();

    }

}

class T_R_C implements Runnable , C2{

    @Override
    public void run() {
        while (true){
            f();
        }
    }

    @Override
    public void f() {
        System.out.println("test double interface...");
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {e.printStackTrace();}
    }
}

interface C2{
    void f();
}

