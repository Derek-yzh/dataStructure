package org.example.designPattern.T_05_adapter.interfaceAdapter.objectAdapter;

public class VoltageAdapter extends AbstractIVoltage5V {

    private Voltage220V voltage220V;

    public VoltageAdapter(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }

    @Override
    public int output5V() {
        if (voltage220V != null) {
            int src = voltage220V.output220v();
            return src/44;
        }
        return 0;
    }

}
