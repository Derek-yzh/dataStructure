package org.example.designPattern.T_09_facade;

public class DVD {
    private static DVD instance = new DVD();

    public static DVD getInstance() {
        return instance;
    }

    public void on(){
        System.out.println(" dvd on...");
    }

    public void off(){
        System.out.println(" dvd off...");
    }

    public void play(){
        System.out.println(" dvd play...");
    }

    public void pause(){
        System.out.println(" dvd pause...");
    }

}
