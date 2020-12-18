package org.example.designPattern.T_01_singlePattern.type6;

/**
 * 2020-07-13 13:37:49
 * 单例模式:双重检查
 *   优缺点说明:
 *     1) Double-Check概念 是多线程开发中常使用到的，如代码中所示，我们进行了两
 *      次if (singleton == nul)检查，这样就可以保证线程安全了。
 *     2)这样，实例化代码只用执行--次，后面再次访问时，判断if (singleton == null),
 *      直接return实例化对象，也避免的反复进行方法同步.
 *     3)线程安全;延迟加载;效率较高
 *     4)结论:在实际开发中，推荐使用这种单例设计模式
 */
public class SingleTest06 {
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
    private static volatile Singleton instance;
    //提供一个静态的公有方法，加入双重检查代码，解决线程安全问题，同时解决懒加载问题
    //同时保证了效率,推荐使用
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}