package org.example.algorithm.array;

/**
 *2020-07-05 07:06:34
 */
public class MyArrayStack {

    private int maxSize;//栈大小
    private int[] stack;//数组模拟栈
    private int top = -1;//top表示栈顶，初始化为-1

    public MyArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    /**
     * 判断栈满
     * @return true为满
     */
    public boolean isFull(){
        return top == maxSize - 1;
    }

    /**
     * 判断栈空
     * @return true为空
     */
    public boolean isEmpty(){
        return top == -1;
    }

    /**
     * 入栈
     * @param x 入栈元素
     */
    public void push(int x){
        if (isFull()){
            System.out.println("栈已满！");
            return;
        }
        stack[++top] = x;
    }

    /**
     * 出栈
     * @return 返回出栈元素
     */
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空！");
        }
        return stack[top--];
    }

    /**
     * 遍历栈
     */
    public void show(){
        if (isEmpty()){
            System.out.println("栈空!");
            return;
        }
        int cur = top;
        while (cur != -1){
            System.out.println("stack["+cur+"]="+stack[cur--]);
        }
    }

    /**
     * 返回运算符优先级，优先级是程序员来确定，优先级用数字来表示
     * 数字越大，优先级越高
     * @param operate 算数符
     * @return 返回运算符优先级
     */
    public int priority(int operate){
        if (operate == '*' || operate == '/'){
            return 1;
        }else if (operate == '+' || operate == '-'){
            return 0;
        }else if (operate == ')'){
            return 2;
        }else {
            return -1;
        }
    }

    /**
     * 判断是不是一个运算符
     * @param val 传入的运算符
     * @return true为是
     */
    public boolean isOperate(char val){
        return val == '+' || val == '-' || val == '*' || val == '/' || val =='(' || val == ')';
    }

    /**
     * 计算方法
     * @param num1 参与运算的值1
     * @param num2 参与运算的值1
     * @param operate 参与运算的符号
     * @return 返回结果
     */
    public int cal(int num1, int num2, int operate){
        int res = 0;//存放结果
        switch (operate){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

    /**
     * 查看栈顶元素
     * @return 返回栈顶元素
     */
    public int peek(){
        return stack[top];
    }

}














