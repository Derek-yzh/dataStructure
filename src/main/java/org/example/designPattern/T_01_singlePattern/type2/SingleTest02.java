package org.example.designPattern.T_01_singlePattern.type2;

/**
 * 2020-07-13 13:37:49
 * 单例模式:饿汉式(静态常量)
 *   优缺点说明:
 *     1)这种方式和上面的方式其实类似，只不过将类实例化的过程放在了静态代码块中，
 *       也是在类装载的时候，就执行静态代码块中的代码，初始化类的实例。优缺点和上面是一一样的。
 *     2)结论:这种单例模式可用，但是可能造成内存浪费
 */
public class SingleTest02 {
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
    private static  Singleton instance;
    static {instance = new Singleton();}
    public static Singleton getInstance() {
        return instance;
    }
}