package org.example.designPattern.T_01_singlePattern.type8;

/**
 * 2020-07-13 16:16:30
 * 单例模式(枚举)
 *     1)这借助JDK15中添加的枚举来实现单例模式。不仅能避免多线程同步问题，而
 *       且还能防止反序列化重新创建新的对象。
 *     2)这种方式是Effective Java作 者Josh Bloch 提倡的方式
 *     3)结论:推荐使用
 */
public class SingleTest08 {
    public static void main(String[] args) {
        Singleton instance = Singleton.INSTANCE;
        Singleton instance2 = Singleton.INSTANCE;
        System.out.println(instance == instance2);

        System.out.println(instance.hashCode());
        System.out.println(instance2.hashCode());

        instance.sayOK();
        instance2.sayOK();

    }
}


enum Singleton {
    INSTANCE;
    public void sayOK(){
        System.out.println("OK!");
    }
}