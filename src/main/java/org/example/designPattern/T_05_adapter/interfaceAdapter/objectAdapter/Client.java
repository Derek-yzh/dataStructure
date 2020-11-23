package org.example.designPattern.T_05_adapter.interfaceAdapter.objectAdapter;

public class Client {
    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.charge(new VoltageAdapter(new Voltage220V()));
    }
}
