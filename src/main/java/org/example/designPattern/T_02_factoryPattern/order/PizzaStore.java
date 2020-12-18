package org.example.designPattern.T_02_factoryPattern.order;

import org.example.designPattern.T_02_factoryPattern.AbsFactory;
import org.example.designPattern.T_02_factoryPattern.BJFactory;

/**
 * 相当于一个客户端，发出订购
 */
public class PizzaStore {
    public static void main(String[] args) {
        //System.out.println(new SimpleFactory().createPizza("chess"));//简单工厂
        //new LDOrderPizza();//工厂方法
        AbsFactory factory = new BJFactory();//抽象工厂模式
        System.out.println(factory.createPizza("greek"));
    }
}
