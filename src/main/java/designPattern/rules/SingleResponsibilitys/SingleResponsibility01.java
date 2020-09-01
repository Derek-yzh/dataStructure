package designPattern.rules.SingleResponsibilitys;

/**
 * 2020-07-10 14:53:39
 * 单一职责原则
 */
public class SingleResponsibility01 {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        vehicle.run("摩托车");
        vehicle.run("汽车");
        vehicle.run("飞机");
    }
}

/**
 * 交通工具类
 * 方式一：
 *  1.run方法中违反了单一职责原则
 *  2.解决方案：根据交通工具运行的方式不同，分解成不同的类 ===>> 方案二
  */
class Vehicle{
    public void run(String vehicle){
        System.out.println(vehicle+" 在公路上行使...");
    }
}
