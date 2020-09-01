package designPattern.builder.improve;

/**
 * 抽象的建造者
 */
public abstract class HouseBuilder {
    protected House house = new House();
    public abstract void buildBasic();
    public abstract void buildWall();
    public abstract void roofed();
    public House buildHouse(){
        return house;
    }
}
