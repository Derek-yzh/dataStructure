package designPattern.proxy.dynamicproxy;

public class TeacherDaoImpl implements TeacherDao {
    @Override
    public void teach() {
        System.out.println("老师授课中...");
    }
}
