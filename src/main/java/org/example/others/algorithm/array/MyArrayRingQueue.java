package org.example.others.algorithm.array;

import org.example.others.dataInterface.MyList;

/**
 * 2020-07-02 14:41:49
 * 数组模拟环形队列
 */
public class MyArrayRingQueue<T> implements MyList<T> {

    private int maxSize;//数组最大容量
    private int front;//队列头
    private int rear;//队列尾
    private T[] arr;//该数组用于存放数据，模拟队列

    /**
     * 初始化
     * @param maxSize
     */
    public MyArrayRingQueue(int maxSize){
        this.maxSize = maxSize + 1;
        arr = (T[])new Object[maxSize + 1];
        front = 0;//指向队列头部，指向队列第一个数据
        rear = 0;//指向队列尾部，指向队列的最后一个数据的后一个位置
    }

    @Override
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    @Override
    public boolean isEmpty() {
        return rear == front;
    }

    @Override
    public void add(T n) {
        if (isFull()){
            System.out.println("队列满了，不能加入数据！");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    @Override
    public T get() {
        if (isEmpty()){
            throw new RuntimeException("队列为空，无法取出元素！");
        }
        T o = arr[front];
        front = (front + 1) % maxSize;
        return o;
    }

    @Override
    public void show() {
        if (isEmpty()){
            System.out.println("队列为空，没有数据！");
            return;
        }
        for (int i = front; i < front + this.size(); i++) {
            System.out.print(arr[i%maxSize]+" ");
        }
        System.out.println();
    }

    @Override
    public T first() {
        if (isEmpty()){
            throw new RuntimeException("队列为空,没有数据！");
        }
        return arr[front];
    }

    @Override
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

}
