package org.example.designPattern.T_01_singlePattern.type7;

/**
 * 2020-07-13 13:37:49
 * 单例模式:静态内部类
 *   优缺点说明:
 *     1)这种方式采用了类装载的机制来保证初始化实例时只有一个线程。
 *     2)静态内部类方式在Singleton类被装载时并不会立即实例化，而是在需要实例化时，
 *       调用getInstance方法， 才会装载SingletonInstance类，从而完成Singleton的实例化。
 *     3)类的静态属性只会在第-次加载类的时候初始化，所以在这里，JVM帮助我们
 *      保证了线程的安全性，在类进行初始化时，别的线程是无法进入的。
 *     4)优点:避免了线程不安全，利用静态内部类特点实现延迟加载，效率高
 *     5)结论:推荐使用.
 */
public class SingleTest07 {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance.hashCode());
        System.out.println(instance2.hashCode());
    }
}

class Singleton{
    //构造器私有化
    private Singleton(){
    }
    //写一个静态内部类，有一个静态属性
    private static class SingletonInstance{
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonInstance.INSTANCE;
    }
}