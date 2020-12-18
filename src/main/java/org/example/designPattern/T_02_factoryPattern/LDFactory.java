package org.example.designPattern.T_02_factoryPattern;

import org.example.designPattern.T_02_factoryPattern.pizza.*;

public class LDFactory implements AbsFactory {
    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        System.out.println("使用抽象工厂模式");
        if (orderType.equals("greek")){
            pizza = new LDGreekPizza();
        }else if (orderType.equals("chess")){
            pizza = new LDChessPizza();
        }else {
            return pizza;
        }
        pizza.setName(orderType);
        return pizza;
    }
}
