package org.example.dataStructure;

import org.example.algorithm.array.MyArrayStack;

import java.util.*;

/**
 * 2020-07-05 08:36:14
 * 用两个栈实现简易计算器 main1
 *
 * 2020-07-06 10:05:22
 *
 */
public class CalculatorByStack {

    /**
     * 中缀表达式
     * @param args
     */
    public static void mainMid(String[] args) {
        String expression = "(7+20)-6";
        MyArrayStack numStack = new MyArrayStack(10);
        MyArrayStack opeStack = new MyArrayStack(10);
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int ope = 0;
        int res = 0;
        char ch = ' ';
        String keepNum = "";//用于拼接多位数
        boolean flag = false;//用于判断')'
        while (index < expression.length()){
            ch = expression.substring(index,index+1).charAt(0);
            if (opeStack.isOperate(ch)){
                if (opeStack.isEmpty()){
                    opeStack.push(ch);
                }else {
                    if (opeStack.priority(ch) <= opeStack.priority(opeStack.peek())){
                        if (opeStack.peek() == ')'){
                            opeStack.pop();
                            flag = true;
                        }
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        ope = opeStack.pop();
                        res = numStack.cal(num1,num2,ope);
                        if (flag){
                            opeStack.pop();
                            flag = false;
                        }
                        numStack.push(res);
                    }
                    opeStack.push(ch);
                }
            }else {
                //因为ch是字符 在ASCII编码表中数字字符例如'1'值为49 所有要减去48
                //numStack.push(ch - 48);//无法处理多位数
                keepNum += ch;
                while (index < expression.length()-1){
                    char c = expression.substring(index + 1, index + 2).charAt(0);
                    if (opeStack.isOperate(c)){
                        break;
                    }else {
                        keepNum += c;
                    }
                    index++;
                }
                numStack.push(Integer.parseInt(keepNum));
                keepNum = "";
            }
            index++;
        }

        while (!opeStack.isEmpty()){
            num1 = numStack.pop();
            num2 = numStack.pop();
            ope = opeStack.pop();
            res = numStack.cal(num1,num2,ope);
            numStack.push(res);
        }
        System.out.println(expression + "=" + numStack.peek());

    }

    /**
     * 2020-07-06 11:17:41
     * 后缀表达式
     * 逆波兰计算器
     */
    public static void main(String[] args) {
        String infixExpr = "1+((2+3)*4)-5";
        List<String> list = toList(infixExpr);
        List<String> suffix = infixToSuffix(list);
        System.out.println(list);
        System.out.println(calculate(suffix));
    }

    /**
     * 中缀转后缀
     * @param list 中缀list
     * @return 返回后缀list
     */
    private static List<String> infixToSuffix(List<String> list) {
        ArrayList<String> l = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        for (String s : list) {
            if (s.matches("\\d")){
                l.add(s);
            }else if (s.equals("(")){
                stack.push(s);
            }else if (s.equals(")")){
                while (!stack.peek().equals("(")){
                    l.add(stack.pop());
                }
                stack.pop();
            }else {
                while (stack.size() != 0 && Operation.getValue(stack.peek()) >= Operation.getValue(s)){
                    l.add(stack.pop());
                }
                stack.push(s);
            }
        }

        while (stack.size() != 0){
            l.add(stack.pop());
        }

        return l;
    }

    /**
     * 将中缀表达式转成对应的list
     * @param infix 中缀表达式字符串
     * @return  中缀表达式list
     */
    public static List<String> toList(String infix) {
        ArrayList<String> list = new ArrayList<>();
        int i = 0;
        String str;
        char c;
        do {
            //如果c是一个非数字
            if ((c=infix.charAt(i)) < 48 || (c=infix.charAt(i)) > 57 ){
                list.add(""+c);
                i++;
            }else {
                str = "";
                while (i < infix.length() && (c=infix.charAt(i)) >= 48 && (c=infix.charAt(i)) <= 57){
                    str += c;
                    i++;
                }
                list.add(""+str);
            }
        }while (i < infix.length());
        return list;
    }

    /**
     * 将表达式放入list中
     * @param exp 表达式
     * @return list
     */
    public static List<String> getListString(String exp){
        String[] split = exp.split(" ");
        return new ArrayList<String>(Arrays.asList(split));
    }

    /**
     * 运算
     * @param list 后缀表达式
     * @return 返回结果
     */
    public static int calculate(List<String> list){
        Stack<String> stack = new Stack<>();
        int result = 0;
        for (String s : list) {
            if (s.matches("\\d+")){//匹配的是多位数
                stack.push(s);
            }else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                switch (s) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num1 / num2;
                        break;
                    default:
                        throw new RuntimeException("运算符有误！");
                }
                stack.push(String.valueOf(result));
            }
        }
        return result;
    }

}

class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation){
        switch (operation){
            case "+":
                return ADD;
            case "-":
                return SUB;
            case "*":
                return MUL;
            case "/":
                return DIV;
            case "(":
                return 0;
            default:
                throw new RuntimeException("不存在改运算符!");
        }
    }

}