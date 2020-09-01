package designPattern.rules.inversion;

/**
 * 2020-07-10 21:18:30
 * 依赖倒转原则
 */
public class DependencyInversion02 {
    public static void main(String[] args) {
        Person2 person = new Person2();
        person.receive(new WeChat());
        person.receive(new Email2());
    }
}

interface IReceiver{
    String getInfo();
}

class Email2 implements IReceiver{
    @Override
    public String getInfo(){
        return "电子邮件的信息：hello,world!";
    }
}

class WeChat implements IReceiver{
    @Override
    public String getInfo() {
        return "微信的信息：hello,world!";
    }
}

/**
 * 完成Person接收信息的功能
 * 方式二分析：
 *  使用依赖倒转原则
 *      依赖关系传递的三种方式
 *          1)接口传递
 *          2)构造方法传递
 *          3) setter为式传递
 */
class Person2{
    public void receive(IReceiver ir){
        System.out.println(ir.getInfo());
    }
}
