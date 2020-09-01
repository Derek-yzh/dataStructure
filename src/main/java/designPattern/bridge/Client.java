package designPattern.bridge;

public class Client {
    public static void main(String[] args) {
        FoldedPhone phone = new FoldedPhone(new HuaWei());
        phone.open();
        System.out.println("==========");
        phone = new FoldedPhone(new XiaoMi());
        phone.open();
    }
}
