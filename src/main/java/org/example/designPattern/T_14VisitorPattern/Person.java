package org.example.designPattern.T_14VisitorPattern;

/**
 * @Author: Derek
 * @DateTime: 2020/12/15 19:44
 * @Description: TODO
 */
public abstract class Person {

    public Person() {
    }

    public abstract void accept(Action action);

}
