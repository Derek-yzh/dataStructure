package org.example.jvm;

import java.util.Random;

/**
 * 2020-07-04 18:32:09
 * JVM
 *  1.JVM系统架构图
 *
 *  2.类加载器 (看程序结果)
 *   2.1 有哪几种类加载器
 *   2.2 双亲委派
 *   2.3 沙箱安全机制
 *
 *  3.Native
 *   3.1 native是一个关键字
 *   3.2 生命有，无实现，why？
 *
 *  4.PC寄存器
 *   4.1 记录了方法之间调用和执行情况，类似排版值日表
 *       用来存储指向下一条命令的地址，也即将要执行的指令代码
 *       它是当前线程所执行的字节码的行号指示器
 *
 *  5.方法区
 *   5.1 它存储了每一个类的结构信息
 *   5.2 方法区是规范，在不同的虚拟机里头实现是不一样的，最典型的是永久代(PermGen space)和元空间(Metaspace)
 *    实现：java7及以前：永久代 java8：元空间
 *    实例变量存在堆内存中，和方法区无关
 *
 *  6.stack
 *    6.1 栈管运行，堆管存储  程序 = 框架 + 业务逻辑 程序 = 算法 + 数据结构
 *    6.2 栈保存了哪些东东？
 *     8种基本类型的变量+对象的引用变量+实例方法 都是在函数的栈内存中分配
 *     java 方法 = 栈帧
 *     栈帧中主要保存三类东西：
 *       本地变量：输入参数和输出参数以及方法内的变量
 *       栈操作：记录出栈、入栈的操作
 *       栈帧数据：包括类文件、方法等等
 *     栈运行原理:
 *          栈中的数据都是以栈帧(Stack Frame)的格式存在，栈帧是一个内存区
 *          块，是一个数据集，是一个有关方法(Method)和运行期数据的数据集，
 *          当一个方法A被调用时就产生了一个栈帧F1，并被压入到栈中，
 *          A方法又调用了B方法，于是产生栈帧F2也被压入栈，
 *          B方法又调用了C方法， 于是产生栈帧F3也被压入栈，
 *          执行完毕后，先弹出F3栈帧，再弹出F2栈帧，再弹出F1栈帧....
 *          遵循"先进后出"/"后进先出"原则。
 *
 *          每个方法执行的同时都会创建一个栈帧，用于存储局部变量表、操作数栈、动态链接、方法出口等信息，
 *          每一个方法从调用直至执行完毕的过程，就对应着一个栈帧在虚拟机中入栈到出栈的过程。
 *          栈的大小和具体JVM的实现有关，通常在256K~756K之间,与等于1Mb左右。
 *     6.3 java.lang.StackOverflowError sof
 *
 *  7. heap
 *    新生代中内存比例：Eden:From:To = 8:1:1
 *    堆中内存比例：新生代：老年代 = 1：2
 *    MinorGC的过程：赋值->清空->交换     Minor:次要
 *    GC之后有交换 谁空谁是To
 *  8. heap --> 对象的生命周期 --> OOM异常
 *    Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 *    8.1 老师讲解的heap结构是否正确?
 *    8.2 -Xms1024m -Xmx1024m -XX:+PrintGCDetails
 *        -XX:+PrintGCDetails =》-Xlog:gc*
 *
 *      JVM在进行GC时，并非每次都对上面三个内存区域一起回收的， 大部分时候回收的都是指新生代。
 *      因此GC按照回收的区域又分了两种类型，
 *      一种是普通GC (minor GC)，-种是全局GC (major GC or Full GC)
 *
 *      Minor GC称Full GC的区别
 *       普通GC (minor GC) :只针对新生代区域的GC,指发生在新生代的垃圾收集动作，
 *       因为大多数Java对象存活率都不高，所以MinorGC非常频繁，一般回收速度也比较快。
 *       全局GC (major GC or Full GC) :指发生在老年代的垃圾收集动作，出现了MajorGC,
 *       经常会伴随至少一次的MinorGC(但并不是绝对的)。MajorGC的速度-般要比MinorGC慢上10倍以上
 *
 */
public class JVMObject {
    public static void main(String[] args) {
        //测试ClassLoader
        /*Object o = new Object();
        //        //System.out.println(o.getClass().getClassLoader().getParent().getParent());
        //System.out.println(o.getClass().getClassLoader().getParent());
        System.out.println(o.getClass().getClassLoader());//JDK自带的走bootstrap类加载器 所以为null

        JVMObject object = new JVMObject();
        System.out.println(object.getClass().getClassLoader().getParent().getParent());
        System.out.println(object.getClass().getClassLoader().getParent());
        System.out.println(object.getClass().getClassLoader());

        System.out.println(5 >> 1);*/
        System.out.println("核数："+Runtime.getRuntime().availableProcessors());//核数

        //JVM最大内存量
        long maxMemory = Runtime.getRuntime().maxMemory();
        //JVM内存总量
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("-Xmx:MAX_MEMORY = " + maxMemory + "(字节) " + (maxMemory / 1024 / 1024 ) + "MB");
        System.out.println("-Xms:TOTAL_MEMORY = " + totalMemory + "(字节) " + (totalMemory / 1024 / 1024 ) + "MB");

    }

    /**
     * 测试OOM
     * OutOfMemoryError
     */
    public static void mai() {
        String str = "sssss";
        while (true){
            str += str + new Random(8888888)+ new Random(999999999);
        }
    }


}


















