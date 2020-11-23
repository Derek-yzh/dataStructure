package org.example.designPattern.T_07_decorator;

public class Chocolate extends Decorator {

    public Chocolate(Drink drink) {
        super(drink);
        setDes("巧克力");
        setPrice(3f);
    }

}
