package org.example.designPattern.T_10_flyweight;

public class ConcreteWebSite extends WebSite{

    private String type = "";

    public ConcreteWebSite(String type) {
        this.type = type;
    }

    @Override
    public void use(User user) {
        System.out.println(type+"使用者:"+user.getName());
    }
}
