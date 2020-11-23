package org.example.designPattern.T_04_builder.improve;

public class CommonHouse extends HouseBuilder {
    @Override
    public void buildBasic() {
        System.out.println("base...");
    }

    @Override
    public void buildWall() {
        System.out.println("wall...");
    }

    @Override
    public void roofed() {
        System.out.println("roofed...");
    }
}
