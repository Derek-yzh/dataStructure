package designPattern.rules.segregations;

/**
 * 2020-07-10 15:42:59
 * 接口隔离原则
 * A2依赖于B2 但只会用到1，2，3方法
 * C2依赖于D2 但只会用到1，4，5方法
 */
public class Segregation02 {
    public static void main(String[] args) {
        A2 a2 = new A2();
        a2.depend1(new B2());//A类通过接口依赖B类
        a2.depend2(new B2());
        a2.depend3(new B2());
        C2 c2 = new C2();
        c2.depend1(new D2());
        c2.depend4(new D2());
        c2.depend5(new D2());
    }
}
interface Interface2{
    void operation1();
}
interface InterfaceB{
    void operation2();
    void operation3();
}
interface InterfaceD{
    void operation4();
    void operation5();
}
class B2 implements Interface2,InterfaceB{
    @Override
    public void operation1() {
        System.out.println("B 实现了 operation1");
    }
    @Override
    public void operation2() {
        System.out.println("B 实现了 operation2");
    }
    @Override
    public void operation3() {
        System.out.println("B 实现了 operation3");
    }
}
class D2 implements Interface2,InterfaceD{
    @Override
    public void operation1() {
        System.out.println("D 实现了 operation1");
    }
    @Override
    public void operation4() {
        System.out.println("D 实现了 operation4");
    }
    @Override
    public void operation5() {
        System.out.println("D 实现了 operation5");
    }
}
class A2 {
    public void depend1(Interface2 i){
        i.operation1();
    }
    public void depend2(InterfaceB i){
        i.operation2();
    }
    public void depend3(InterfaceB i){
        i.operation3();
    }
}
class C2 {
    public void depend1(Interface2 i){
        i.operation1();
    }
    public void depend4(InterfaceD i){
        i.operation4();
    }
    public void depend5(InterfaceD i){
        i.operation5();
    }
}