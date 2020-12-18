package org.example.designPattern.T_12_template;

public abstract class SoyaMilk {

    final void make(){
        select();
        if (need()) {
            addCondiments();
        }
        soak();
        beat();
    }

    void select(){
        System.out.println("第一步：选择黄豆");
    }

    abstract void addCondiments();

    void soak(){
        System.out.println("第三步黄豆配料开始浸泡");
    }

    void beat(){
        System.out.println("第四步：黄豆和豆浆机放到豆浆机中加工");
    }

    /**
     * 钩子函数
     * @return
     */
    boolean need(){
        return true;
    }

}
