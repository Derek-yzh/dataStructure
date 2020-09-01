package designPattern.factoryPattern.order;

import designPattern.factoryPattern.pizza.BJChessPizza;
import designPattern.factoryPattern.pizza.BJGreekPizza;
import designPattern.factoryPattern.pizza.Pizza;

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
