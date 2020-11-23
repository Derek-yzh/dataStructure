package org.example.designPattern.T_01_singlePattern.type3;

/**
 * 2020-07-13 13:37:49
 * 单例模式:懒汉式
 *   优缺点说明:
 *     1) 起到了Lazy Loading的效果， 但是只能在单线程下使用。
 *     2) 如果在多线程下，一个线程进入了if (singleton == null)判断语句块，还未来得及往下执行，
 *       另一个线程也通过了这个判断语句，这时便会产生多个实例。所以在多线程环境下不可使用这种方式
 *     3) 结论:在实际开发中，不要使用这种方式.
 */
public class SingleTest03 {
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
    //提供一个静态方法，当使用方法时，才去创建instance
    public static Singleton getInstance() {
        if (instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}