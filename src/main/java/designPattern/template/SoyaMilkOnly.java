package designPattern.template;

public class SoyaMilkOnly extends SoyaMilk{
    @Override
    void addCondiments() {

    }

    @Override
    boolean need() {
        return false;
    }
}
