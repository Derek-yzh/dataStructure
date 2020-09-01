package org.example.juc;

class Ttv{
    private String name;
    public Ttv(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

/**
 * 2020-07-05 13:44:55
 * 测试值传递
 */
public class TestTransferValue07 {
    public void changeValue(int age){ age = 30; }
    public void changeValue(Ttv ttv){ ttv.setName("xxx"); }
    public void changeValue(String str){ str = "xxx"; }

    public static void main(String[] args) {
        TestTransferValue07 test = new TestTransferValue07();
        int age = 20;
        test.changeValue(age);
        System.out.println("age----"+age);

        Ttv person = new Ttv("abc");
        test.changeValue(person);
        System.out.println("personName----"+person.getName());

        String s = "xxx";
        String str = "abc";
        test.changeValue(str);
        System.out.println("String----"+str);


    }

}
