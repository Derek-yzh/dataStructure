package org.example.designPattern.T_02_factoryPattern;

import org.example.designPattern.T_02_factoryPattern.pizza.ChessPizza;
import org.example.designPattern.T_02_factoryPattern.pizza.GreekPizza;
import org.example.designPattern.T_02_factoryPattern.pizza.Pizza;

/**
 * 2020-07-13 18:30:58
 * 简单工厂模式
 *
 */
public class SimpleFactory {
    public Pizza createPizza(String orderType){
        Pizza pizza = null;
        System.out.println("使用简单工厂模式");
        if (orderType.equals("greek")){
            pizza = new GreekPizza();
        }else if (orderType.equals("chess")){
            pizza = new ChessPizza();
        }else {
            return pizza;
        }
        pizza.setName(orderType);
        return pizza;
    }

    public static Pizza createPizzaStatic(String orderType){
        Pizza pizza = null;
        System.out.println("使用简单工厂模式");
        if (orderType.equals("greek")){
            pizza = new GreekPizza();
        }else if (orderType.equals("chess")){
            pizza = new ChessPizza();
        }else {
            return pizza;
        }
        pizza.setName(orderType);
        return pizza;
    }
}
