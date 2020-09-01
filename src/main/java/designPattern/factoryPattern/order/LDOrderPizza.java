package designPattern.factoryPattern.order;

import designPattern.factoryPattern.pizza.*;

/**
 * 2020-07-13 16:59:53
 */
public class LDOrderPizza extends OrderPizza{

    @Override
    Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if (orderType.equals("chess")){
            pizza = new LDChessPizza();
        }else if (orderType.equals("greek")){
            pizza = new LDGreekPizza();
        }
        return pizza;
    }

}
