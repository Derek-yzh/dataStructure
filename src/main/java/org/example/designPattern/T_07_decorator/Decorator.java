package org.example.designPattern.T_07_decorator;

public class Decorator extends Drink {

    private Drink drink;

    public Decorator(Drink drink) {
        this.drink = drink;
    }

    @Override
    public float cost() {
        return super.getPrice() + drink.cost();
    }

    @Override
    public String getDes() {
        return drink.getDes()+" && "+
                super.getDes()+super.getPrice();
    }
}
