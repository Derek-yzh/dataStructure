package designPattern.builder.improve;

public class Client {
    public static void main(String[] args) {
        CommonHouse commonHouse = new CommonHouse();
        HighBuilding highBuilding = new HighBuilding();
        HouseDirector houseDirector = new HouseDirector(commonHouse);
        House house = houseDirector.constructHouse();

        System.out.println("======================");
        houseDirector.setHouseBuilder(highBuilding);
        houseDirector.constructHouse();
    }
}
