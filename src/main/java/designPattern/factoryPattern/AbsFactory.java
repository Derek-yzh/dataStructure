package designPattern.factoryPattern;

import designPattern.factoryPattern.pizza.Pizza;

public interface AbsFactory {
    Pizza createPizza(String type);
}
