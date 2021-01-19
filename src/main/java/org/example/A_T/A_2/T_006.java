package org.example.A_T.A_2;

import java.util.Stack;

/**
 * @Author: Derek
 * @DateTime: 2020/11/21 16:23
 * @Description: （2）
 *      给你一个栈，请你逆序这个栈，不嫩用额外的数据结构，只能使用递归函数，如何实现
 */
public class T_006 {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        f(stack);

        System.out.println(stack);
    }

    public static void f(Stack<Integer> stack){

        if (!stack.isEmpty()){
            int last = fun(stack);
            f(stack);
            stack.push(last);
        }

    }

    /**
     * 取出栈底元素
     * @param stack stack
     * @return 栈底元素
     */
    public static int fun(Stack<Integer> stack){
        int result = stack.pop();
        if (stack.isEmpty()){
            return result;
        }else {
            int last = fun(stack);
            stack.push(result);
            return last;
        }
    }


}
