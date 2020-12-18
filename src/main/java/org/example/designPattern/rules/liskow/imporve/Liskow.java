package org.example.designPattern.rules.liskow.imporve;

/**
 * 2020-07-10 22:06:35
 * 里氏替换原则
 *
 * 通过组合(在B中加一个属性A)降低耦合性
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
//创建一个更加基础的基类
class Base{

}

class A extends Base{
    public int fun1(int num1, int num2){
        return num1-num2;
    }
}

class B extends Base {
    private A a = new A();
    public int fun1(int num1, int num2){
        return this.a.fun1(num1,num2);
    }
    public int fun2(int num1, int num2){
        return this.a.fun1(num1,num2)+9;
    }
}