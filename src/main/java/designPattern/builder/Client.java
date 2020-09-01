package designPattern.builder;

public class Client {
    public static void main(String[] args) {
        AbstractHouse house = new CommonHouse();
        house.build();
    }
}
