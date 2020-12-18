package org.example.designPattern.T_02_factoryPattern.order;

import org.example.designPattern.T_02_factoryPattern.pizza.Pizza;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 订购披萨类（组合）
 * 2020-07-13 16:59:53
 */
public abstract class OrderPizza {

    abstract Pizza createPizza(String orderType);

    public OrderPizza(){
        Pizza pizza = null;
        String orderType = "";
        do {
            orderType = getType();
            pizza = createPizza(orderType);
            if (pizza == null){
                return;
            }
            pizza.prepare();
        }while (true);
    }

    private String getType(){
        try {
            BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input pizza type : ");
            String str = strin.readLine();
            return str;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

}
