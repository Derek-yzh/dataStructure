package designPattern.composite;

public class Client {
    public static void main(String[] args) {
        University university = new University("清华大学", "中国顶级大学");
        College college1 = new College("计算机学院", "计算机学院");
        College college2 = new College("工程学院", "工程学院");
        college1.add(new Department("软件工程","软件工程"));
        college1.add(new Department("计算机科学与技术","计算机科学与技术"));
        college1.add(new Department("人工智能","人工智能"));
        college2.add(new Department("建筑","建筑"));
        college2.add(new Department("工程","工程"));

        university.add(college1);
        university.add(college2);

        university.print();
    }
}
