package org.example.designPattern.T_05_adapter;

public class VoltageAdapter extends Voltage220V implements IVoltage5V{

    @Override
    public int output5V() {
        int src = output220v();
        return src/44;
    }

}
