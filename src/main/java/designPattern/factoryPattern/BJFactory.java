package designPattern.factoryPattern;

import designPattern.factoryPattern.pizza.*;

public class BJFactory implements AbsFactory {
    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        System.out.println("使用抽象工厂模式");
        if (orderType.equals("greek")){
            pizza = new BJGreekPizza();
        }else if (orderType.equals("chess")){
            pizza = new BJChessPizza();
        }else {
            return pizza;
        }
        pizza.setName(orderType);
        return pizza;
    }
}
