package designPattern.template;

public class Client {
    public static void main(String[] args) {
        System.out.println("红豆制成的豆浆：");

        RedBeanSoyaMilk redBeanSoyaMilk = new RedBeanSoyaMilk();
        redBeanSoyaMilk.make();

        System.out.println("===========================");

        System.out.println("花生制成的豆浆：");
        PeanutSoyaMilk peanutSoyaMilk = new PeanutSoyaMilk();
        peanutSoyaMilk.make();

        System.out.println("===========================");

        System.out.println("纯豆浆：");
        SoyaMilkOnly  soyaMilkOnly= new SoyaMilkOnly();
        soyaMilkOnly.make();
    }
}
