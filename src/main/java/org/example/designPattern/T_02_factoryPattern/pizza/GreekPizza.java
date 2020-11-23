package org.example.designPattern.T_02_factoryPattern.pizza;

/**
 * 2020-07-13 16:59:53
 */
public class GreekPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("给制作希腊披萨提供原材料");
    }
}
