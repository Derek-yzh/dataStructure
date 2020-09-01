package designPattern.factoryPattern.pizza;

/**
 * 2020-07-13 16:59:53
 */
public class ChessPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("给制作奶酪披萨提供原材料");
    }
}
