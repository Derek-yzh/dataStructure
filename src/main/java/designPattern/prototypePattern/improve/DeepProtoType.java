package designPattern.prototypePattern.improve;

import java.io.*;

/**
 * 2020-07-15 23:42:25
 * 深拷贝方式一 使用clone方法
 * 深拷贝方式二 使用对象的序列化实现(推荐)
 */
public class DeepProtoType implements Serializable, Cloneable {
    private String name;
    private Person person;

    public DeepProtoType(String name) {
        this.name = name;
    }

    //深拷贝方式二 使用对象的序列化实现
    public Object deepClone(){
        DeepProtoType copy = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            //序列化
            bos  = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            //反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            copy = (DeepProtoType) ois.readObject();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            try {
                bos.close();
                oos.close();
                bis.close();
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return copy;
    }

    //深拷贝方式一 使用clone方法
    @Override
    protected Object clone()  {
        DeepProtoType deep = null;
        try {
            deep = (DeepProtoType)super.clone();//完成基本数据类型和字符串的(属性的)克隆
            //对引用类型的属性进行单独的处理
            deep.setPerson((Person)person.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return deep;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "DeepProtoType{" +
                "name='" + name + '\'' +
                ", person=" + person +
                '}';
    }
}
