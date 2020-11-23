package org.example.designPattern.T_04_builder;

public class CommonHouse extends AbstractHouse {
    @Override
    public void buildBasic() {
        System.out.println("commonBasic...");
    }

    @Override
    public void buildWall() {
        System.out.println("commonWall...");
    }

    @Override
    public void roofed() {
        System.out.println("commonRoofed...");
    }
}
