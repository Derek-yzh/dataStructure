package org.example.designPattern.T_14VisitorPattern;

/**
 * @Author: Derek
 * @DateTime: 2020/12/15 19:44
 * @Description: TODO
 */
public class Success extends Action {
    @Override
    public void getManResult(Man var1) {
        System.out.println("man 给的评价很成功");
    }

    @Override
    public void getWomanResult(Woman var1) {
        System.out.println("woman 给的评价很成功");
    }
}
