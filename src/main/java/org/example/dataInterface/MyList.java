package org.example.dataInterface;

/**
 * 2020-07-02 12:51:23
 * Queue接口
 */
public interface MyList<T> {

    /**
     * 判断队列是否满
     * @return
     */
    public boolean isFull();

    /**
     * 判断队列是否空
     * @return
     */
    public boolean isEmpty();

    /**
     * 添加元素
     * @param n
     */
    public void add(T n);

    /**
     * 取元素
     * @return
     */
    public T get();

    /**
     * 显示所有队列数据
     */
    public void show();

    /**
     * 取出头数据
     * @return
     */
    public T first();

    /**
     * 当前队列有效个数
     * @return
     */
    public int size();
}
