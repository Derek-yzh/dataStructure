package org.example.designPattern.T_05_adapter.interfaceAdapter.objectAdapter;

public class Voltage220V {
    public int output220v(){
        int src = 220;
        System.out.println("电压为："+src);
        return src;
    }
}
