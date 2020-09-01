package designPattern.factoryPattern.order;

import designPattern.factoryPattern.AbsFactory;
import designPattern.factoryPattern.BJFactory;
import designPattern.factoryPattern.SimpleFactory;
import designPattern.factoryPattern.pizza.Pizza;

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
