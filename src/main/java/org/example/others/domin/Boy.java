package org.example.others.domin;

public class Boy{
    private int value;
    private Boy next;

    public Boy(){}
    public Boy(int value) {
        this.value = value;
    }
    public Boy(int value, Boy next) {
        this.value = value;
        this.next = next;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public Boy getNext() {
        return next;
    }
    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "value=" + value +
                '}';
    }
}