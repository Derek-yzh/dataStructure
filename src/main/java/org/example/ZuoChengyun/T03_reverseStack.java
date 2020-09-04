package org.example.ZuoChengyun;

import java.util.HashSet;
import java.util.Stack;

/**
 * 2020-09-03 19:53:42
 * 栈逆序
 */
public class T03_reverseStack {

    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(2);
        stack.push(1);

        System.out.println(stack.toString());
        m(stack);
        System.out.println(stack.toString());


    }

    private static void m(Stack<Integer> stack) {
        if (stack.isEmpty()){
            return;
        }
        Integer pop = f(stack);
        m(stack);
        stack.push(pop);
    }

    /**
     * 删除并返回栈底元素
     * @param stack 栈
     * @return 返回值
     */
    private static Integer f(Stack<Integer> stack) {
        if (stack.size() == 1){
            return stack.pop();
        }
        Integer pop = stack.pop();
        Integer f = f(stack);
        stack.push(pop);
        return f;
    }
}
