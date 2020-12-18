package org.example.dataStructure;

import org.example.others.algorithm.array.MyArrayStack;
import org.example.others.algorithm.link.MyLinkedStack;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

/**
 * 2020-07-05 07:31:25
 * 测试栈
 */
public class TestStack {

    /**
     * 测试数组模拟栈
     * @param args 参数
     */
    public static void main(String[] args) {
        MyArrayStack stack = new MyArrayStack(5);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop){
            System.out.println("show:显示");
            System.out.println("exit:退出");
            System.out.println("push:压栈");
            System.out.println("pop:出栈");
            System.out.println("请输入你的选择：");
            key = scanner.next();
            switch (key){
                case "show":
                    stack.show();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                case "push":
                    System.out.println("请输入一个数：");
                    int i = scanner.nextInt();
                    stack.push(i);
                    break;
                case "pop":
                    try {
                        int pop = stack.pop();
                        System.out.println("出栈的数据为："+pop);
                        break;
                    } catch (Exception e) {
                        System.out.println("栈为空！");
                    }
                default:
                    break;
            }
        }
        System.out.println("程序退出！");
    }

    /**
     * 测试单链表模拟栈
     */
    @Test
    public void testLinkedStack(){
        MyLinkedStack stack = new MyLinkedStack();
        stack.push(1);
        stack.push(4);
        System.out.println(stack.pop());
        stack.show();
    }

}
