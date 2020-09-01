package org.example.algorithm.link;

class StackNode{
    private int value;
    private StackNode next;

    public StackNode(){};
    public StackNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "StackNode{" +
                "value=" + value +
                '}';
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public StackNode getNext() {
        return next;
    }
    public void setNext(StackNode next) {
        this.next = next;
    }
}

/**
 * 2020-07-05 07:52:58
 * 链表模拟栈
 */
public class MyLinkedStack {

    StackNode top = null;

    public boolean isEmpty(){
        return top == null;
    }

    public void push(int val){
        StackNode push = new StackNode(val);
        if (top == null){
            top = push;
            return;
        }
        push.setNext(top);
        top = push;
    }

    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空!");
        }
        StackNode pop = top;
        top = top.getNext();
        return pop.getValue();
    }

    public void show(){
        if (isEmpty()){
            System.out.println("栈空！");
            return;
        }
        StackNode cur = top;
        while (cur != null){
            System.out.println(cur.getValue());
            cur = cur.getNext();
        }
    }

}
