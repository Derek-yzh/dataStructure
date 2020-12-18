package org.example.designPattern.T_04_builder;

public class Client {
    public static void main(String[] args) {
        AbstractHouse house = new CommonHouse();
        house.build();
    }
}
