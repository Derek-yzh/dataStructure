package org.example.designPattern.T_07_decorator;

public class Milk extends Decorator {
    public Milk(Drink drink) {
        super(drink);
        setDes("牛奶");
        setPrice(2f);
    }
}
