package org.example.algorithm.array;

import org.example.dataInterface.MyList;

/**
 * 2020-07-02 14:41:49
 * 数组模拟队列
 */
public class MyArrayQueue<T> implements MyList<T> {

    private int maxSize;//数组最大容量
    private int front;//队列头
    private int rear;//队列尾
    private T[] arr;//该数组用于存放数据，模拟队列

    /**
     * 初始化
     * @param maxSize
     */
    public MyArrayQueue(int maxSize){
        this.maxSize = maxSize;
        arr = (T[]) new Object[maxSize];
        front = -1;//指向队列头部，指向队列第一个数据的前一个位置
        rear = -1;//指向队列尾部，指向队列的最后一个数据
    }

    @Override
    public boolean isFull() {
        return rear == maxSize -1;
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
        rear++;
        arr[rear] = n;
    }

    @Override
    public T get() {
        if (isEmpty()){
            throw new RuntimeException("队列为空，无法取出元素！");
        }
        front++;
        return arr[front];
    }

    @Override
    public void show() {
        if (isEmpty()){
            System.out.println("队列为空，没有数据！");
            return;
        }
        for (int i = front + 1; i <= rear; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    @Override
    public T first() {
        if (isEmpty()){
            throw new RuntimeException("队列为空,没有数据！");
        }
        return arr[front+1];
    }

    @Override
    public int size() {
        return rear - front;
    }

}
