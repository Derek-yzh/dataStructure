package designPattern.proxy.staticproxy;

public class Client {
    public static void main(String[] args) {
        TeacherDao teacher = new TeacherDaoProxy(new TeacherDaoImpl());
        teacher.teach();
    }
}
