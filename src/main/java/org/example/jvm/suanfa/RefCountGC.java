package org.example.jvm.suanfa;

/**
 * 2020-07-05 18:40:54
 * 引用计数算法
 * 难处理循环引用
 */
public class RefCountGC {

    private byte[] bigSize = new byte[2*1024*1024];//这个成员属性的唯一作用就是占用一点内存

    Object instance = null;

    public static void main(String[] args) {
        RefCountGC gc1 = new RefCountGC();
        RefCountGC gc2 = new RefCountGC();
        gc1.instance = gc2;
        gc2.instance = gc1;
        gc1 = null;
        gc2 = null;
        //手动唤起GC
        System.gc();
    }

}
