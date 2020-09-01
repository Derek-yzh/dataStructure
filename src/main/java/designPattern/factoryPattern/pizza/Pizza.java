package designPattern.factoryPattern.pizza;

/**
 * 抽象披萨类
 * 2020-07-13 16:59:53
 */
public abstract class Pizza {
    protected String name;
    //准备原材料，不同的披萨不一样，因此做成抽象方法
    public abstract void prepare();
    public void bake(){
        System.out.println(name+" baking;");
    }
    public void cut(){
        System.out.println(name+" cutting");
    }
    public void box(){
        System.out.println(name+" boxing;");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "name='" + name + '\'' +
                '}';
    }
}
