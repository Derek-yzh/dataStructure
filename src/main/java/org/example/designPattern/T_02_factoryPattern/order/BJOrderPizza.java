package org.example.designPattern.T_02_factoryPattern.order;

import org.example.designPattern.T_02_factoryPattern.pizza.BJChessPizza;
import org.example.designPattern.T_02_factoryPattern.pizza.BJGreekPizza;
import org.example.designPattern.T_02_factoryPattern.pizza.Pizza;

/**
 * 2020-07-13 16:59:53
 */
public class BJOrderPizza extends OrderPizza{

    @Override
    Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if (orderType.equals("chess")){
            pizza = new BJChessPizza();
        }else if (orderType.equals("greek")){
            pizza = new BJGreekPizza();
        }
        return pizza;
    }

}
