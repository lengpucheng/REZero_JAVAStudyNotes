package cn.hll520.java.iosample.other;

import org.junit.Test;

import java.io.*;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-03-11-20:17
 * @since 2021-03-11-20:17
 */
public class ObjectSample {

    @Test
    public void writer() throws IOException, ClassNotFoundException {
        // 序列化
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("src/res/oos.data"));
        oos.writeObject("this is obj");
        oos.writeObject(new Person("lpc",21,99.5));
        oos.close();

        // 反序列化
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream("src/res/oos.data"));
        System.out.println(ois.readObject());
        System.out.println(ois.readObject());
        ois.close();
    }
}

class Person implements Serializable{
    public static final long serialVersionUID = 42222L;
    public String name;
    public int age;
    public double credit;

    public Person(String name, int age, double credit) {
        this.name = name;
        this.age = age;
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", credit=" + credit +
                '}';
    }
}
