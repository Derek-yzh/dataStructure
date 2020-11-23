package org.example.designPattern.T_12_template;

public class Client {
    public static void main(String[] args) {
        System.out.println("红豆制成的豆浆：");

        SoyaMilk redBeanSoyaMilk = new RedBeanSoyaMilk();
        redBeanSoyaMilk.make();

        System.out.println("===========================");

        System.out.println("花生制成的豆浆：");
        SoyaMilk peanutSoyaMilk = new PeanutSoyaMilk();
        peanutSoyaMilk.make();

        System.out.println("===========================");

        System.out.println("纯豆浆：");
        SoyaMilk  soyaMilkOnly= new SoyaMilkOnly();
        soyaMilkOnly.make();


    }
}
