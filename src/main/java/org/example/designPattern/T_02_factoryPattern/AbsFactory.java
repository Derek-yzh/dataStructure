package org.example.designPattern.T_02_factoryPattern;

import org.example.designPattern.T_02_factoryPattern.pizza.Pizza;

public interface AbsFactory {
    Pizza createPizza(String type);
}
