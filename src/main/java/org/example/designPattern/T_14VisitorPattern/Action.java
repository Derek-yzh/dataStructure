package org.example.designPattern.T_14VisitorPattern;

/**
 * @Author: Derek
 * @DateTime: 2020/12/15 19:42
 * @Description: TODO
 */
public abstract class Action {

    public Action() {
    }

    public abstract void getManResult(Man man);

    public abstract void getWomanResult(Woman woman);
}
