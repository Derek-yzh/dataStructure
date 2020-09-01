package designPattern.adapter;

public class Voltage220V {
    public int output220v(){
        int src = 220;
        System.out.println("电压为："+src);
        return src;
    }
}
