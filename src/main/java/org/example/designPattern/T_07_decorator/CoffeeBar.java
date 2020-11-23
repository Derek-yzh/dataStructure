package org.example.designPattern.T_07_decorator;

public class CoffeeBar {

    public static void main(String[] args) {
        Drink order = new LongBlack();
        System.out.println(order.getDes()+"=="+order.cost());

        order = new Milk(order);
        System.out.println(order.getDes()+"=="+order.cost());

        order = new Chocolate(order);
        System.out.println(order.getDes()+"=="+order.cost());

    }

}
