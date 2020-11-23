package org.example.designPattern.T_10_flyweight;

public class Client {
    public static void main(String[] args) {
        WebSiteFactory factory = new WebSiteFactory();
        WebSite webSite1 = factory.getWebSiteCategory("新闻");
        webSite1.use(new User("A"));

        WebSite webSite2 = factory.getWebSiteCategory("test");
        webSite2.use(new User("B"));

        WebSite webSite3 = factory.getWebSiteCategory("新闻");
        webSite3.use(new User("C"));

        WebSite webSite4 = factory.getWebSiteCategory("新闻");
        webSite4.use(new User("D"));

        System.out.println(factory.getWebSiteCount());

        Integer a = 128;
        Integer b = 128;
        System.out.println(a == b);
    }
}
