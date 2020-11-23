package org.example.designPattern.T_06_bridge;

public class Phone {
    private Brand brand;

    public Phone(Brand brand) {
        this.brand = brand;
    }

    protected void open(){
        brand.open();
    }

    protected void close(){
        brand.close();
    }

    protected void call(){
        brand.call();
    }

}
