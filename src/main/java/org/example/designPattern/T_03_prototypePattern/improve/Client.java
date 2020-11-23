package org.example.designPattern.T_03_prototypePattern.improve;

/**
 * 2020-07-15 23:30:15
 * 浅拷贝
 */
public class Client {
    public static void main(String[] args) {
        Sheep sheep = new Sheep("tom", 1, "白色");
        Sheep friend = new Sheep("jack", 2, "黑色");
        sheep.setFriend(friend);

        Sheep sheep2 = (Sheep) sheep.clone();
        Sheep sheep3 = (Sheep) sheep.clone();
        Sheep sheep4 = (Sheep) sheep.clone();
        Sheep sheep5 = (Sheep) sheep.clone();

        System.out.println(sheep3);
        System.out.println(sheep4);
        System.out.println(sheep5);

        System.out.println(sheep == sheep2);
        System.out.println(sheep.getFriend() == sheep2.getFriend());

    }
}
