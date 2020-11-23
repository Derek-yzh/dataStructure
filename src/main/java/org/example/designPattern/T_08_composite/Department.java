package org.example.designPattern.T_08_composite;

public class Department extends OrganizationComponent{

    public Department(String name, String des) {
        super(name, des);
    }

    @Override
    protected void print() {
        System.out.println(getName());
    }
}
