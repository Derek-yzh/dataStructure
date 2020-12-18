package org.example.designPattern.rules.liskow;

/**
 * 2020-07-10 22:06:35
 * 里氏替换原则
 * B类调用父类A类方法时出错
 * 改进方案：==》improve
 */
public class Liskow {
    public static void main(String[] args) {
        A a = new A();
        System.out.println("11-3="+a.fun1(11,3));
        System.out.println("===========");
        B b = new B();
        System.out.println("11-3="+b.fun1(11,3));
        System.out.println("11-3+9="+b.fun2(11,3));

    }
}

class A{
    public int fun1(int num1, int num2){
        return num1-num2;
    }
}

class B extends A{
    //重写父类方法 可能无意识重写错误
    public int fun1(int num1, int num2){
        return num1+num2;
    }
    public int fun2(int num1, int num2){
        return fun1(num1,num2)+9;
    }
}