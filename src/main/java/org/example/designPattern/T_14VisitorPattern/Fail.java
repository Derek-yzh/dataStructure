package org.example.designPattern.T_14VisitorPattern;

/**
 * @Author: Derek
 * @DateTime: 2020/12/15 19:45
 * @Description: TODO
 */
public class Fail extends Action {
    @Override
    public void getManResult(Man var1) {
        System.out.println("man 给的评价很fail");

    }

    @Override
    public void getWomanResult(Woman var1) {
        System.out.println("man 给的评价很fail");

    }
}
