package org.example.designPattern.T_08_composite;

public abstract class OrganizationComponent {
    private String name;
    private String des;

    protected void add(OrganizationComponent organizationComponent){
        throw new UnsupportedOperationException("不支持操作异常");
    }

    protected void remove(OrganizationComponent organizationComponent){
        throw new UnsupportedOperationException("不支持操作异常");
    }

    public OrganizationComponent(String name, String des) {
        this.name = name;
        this.des = des;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    protected abstract void print();
}
