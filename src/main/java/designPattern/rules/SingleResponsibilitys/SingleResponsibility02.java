package designPattern.rules.SingleResponsibilitys;

/**
 * 2020-07-10 14:53:39
 * 单一职责原则
 */
public class SingleResponsibility02 {
    public static void main(String[] args) {
        RoadVehicle roadVehicle = new RoadVehicle();
        roadVehicle.run("摩托车");
        roadVehicle.run("汽车");
        AirVehicle airVehicle = new AirVehicle();
        airVehicle.run("飞机");
        WaterVehicle waterVehicle = new WaterVehicle();
        waterVehicle.run("轮船");

    }
}

/**
 * 方案二
 * 分析：
 *  1.run方法中遵守单一职责原则
 *  2.但这样改动很大，即将类分解，同时修改客户端
 *  3.改进：直接修改Vehicle类，改动的代码会比较少 ==》方案三
 */
class RoadVehicle{
    public void run(String vehicle){
        System.out.println(vehicle+" 在公路上行使...");
    }
}
class AirVehicle{
    public void run(String vehicle){
        System.out.println(vehicle+" 在天上飞...");
    }
}
class WaterVehicle{
    public void run(String vehicle){
        System.out.println(vehicle+" 在水上行使...");
    }
}
