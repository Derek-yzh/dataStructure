package org.example.designPattern.T_01_singlePattern.type4;

/**
 * 2020-07-13 13:37:49
 * 单例模式:懒汉式(线程安全 同步方法)
 *   优缺点说明:
 *     1)解决了线程不安全问题
 *     2)效率太低了，每个线程在想获得类的实例时候，执行getInstance()方 法都要进行同步。
 *       而其实这个方法只执行一次实例化代码就够了，后面的想获得该类实例，
 *       直接return就行了。方法进行同步效率太低
 *     3)结论:在实际开发中，不推荐使用这种方式
 */
public class SingleTest04 {
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
    private static Singleton instance;
    //+synchronized 解决线程不安全问题
    public static synchronized Singleton getInstance() {
        if (instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}