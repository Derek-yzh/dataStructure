package designPattern.rules.SingleResponsibilitys;

/**
 * 2020-07-10 14:53:39
 * 单一职责原则
 */
public class SingleResponsibility03 {
    public static void main(String[] args) {
        Vehicle2 vehicle2 = new Vehicle2();
        vehicle2.run("摩托车");
        vehicle2.runAir("飞机");
        vehicle2.runWater("轮船");
    }
}

/**
 * 方案三
 * 分析：
 *  1.没有对原来的类做大的修改，只是增加了方法
 *  2.虽然没有在类级别上遵循单一职责原则，但在方法级别上仍遵循
 */
class Vehicle2{
    public void run(String vehicle){
        System.out.println(vehicle+" 在公路上行使...");
    }
    public void runAir(String vehicle){
        System.out.println(vehicle+" 在Air上行使...");
    }
    public void runWater(String vehicle){
        System.out.println(vehicle+" 在Water上行使...");
    }
}