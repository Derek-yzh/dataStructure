package designPattern.proxy.dynamicproxy;

public class Client {
    public static void main(String[] args) {
        TeacherDaoImpl teacherDao = new TeacherDaoImpl();
        ProxyFactory factory = new ProxyFactory(teacherDao);
        TeacherDao teacher = (TeacherDao) factory.getProxyInstance();
        teacher.teach();
    }
}
