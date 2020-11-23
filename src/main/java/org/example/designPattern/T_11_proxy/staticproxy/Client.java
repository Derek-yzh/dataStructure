package org.example.designPattern.T_11_proxy.staticproxy;

public class Client {
    public static void main(String[] args) {
        TeacherDao teacher = new TeacherDaoProxy(new TeacherDaoImpl());
        teacher.teach();
    }
}
