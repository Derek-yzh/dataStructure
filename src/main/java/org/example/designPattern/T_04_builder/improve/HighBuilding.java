package org.example.designPattern.T_04_builder.improve;

public class HighBuilding extends HouseBuilder {
    @Override
    public void buildBasic() {
        System.out.println("高楼base");
    }

    @Override
    public void buildWall() {
        System.out.println("高楼wall");
    }

    @Override
    public void roofed() {
        System.out.println("高楼roofed");
    }
}
