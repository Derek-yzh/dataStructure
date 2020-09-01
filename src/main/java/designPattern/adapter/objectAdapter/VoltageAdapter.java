package designPattern.adapter.objectAdapter;

public class VoltageAdapter implements IVoltage5V {

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
