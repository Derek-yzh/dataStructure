package designPattern.adapter.objectAdapter;

public class Phone {

    public void charge(IVoltage5V iVoltage5V){
        if (iVoltage5V.output5V() == 5){
            System.out.println("电压为5V,可以充电");
        }else if (iVoltage5V.output5V() > 5){
            System.out.println("电压过大不能充电!");
        }
    }
}