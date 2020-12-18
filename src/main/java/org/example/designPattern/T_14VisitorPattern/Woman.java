package org.example.designPattern.T_14VisitorPattern;

/**
 * @Author: Derek
 * @DateTime: 2020/12/15 19:43
 * @Description: TODO
 */
public class Woman extends Person {
    @Override
    public void accept(Action action) {
        action.getWomanResult(this);
    }
}
