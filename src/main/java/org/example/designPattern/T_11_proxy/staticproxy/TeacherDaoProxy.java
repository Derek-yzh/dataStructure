package org.example.designPattern.T_11_proxy.staticproxy;

public class TeacherDaoProxy implements TeacherDao {

    private TeacherDao teacherDao;

    public TeacherDaoProxy(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public void teach() {
        System.out.println("代理开始");
        teacherDao.teach();
        System.out.println("代理结束");
    }
}
