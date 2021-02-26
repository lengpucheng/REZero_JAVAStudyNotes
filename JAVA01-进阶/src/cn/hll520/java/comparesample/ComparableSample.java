package cn.hll520.java.comparesample;

import org.junit.Test;

import java.util.Arrays;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-02-26-22:24
 * @since 2021-02-26-22:24
 */
public class ComparableSample {

    @Test
    public void ComparableSort() {
        Person[] peoples = new Person[]{new Person("lpc", 22),
                new Person("lyh", 21),
                new Person("hll",23),
                new Person("alpc",22)
        };
        System.out.println(Arrays.toString(peoples));
        Arrays.sort(peoples);
        System.out.println(Arrays.toString(peoples));
    }
}

class Person implements Comparable<Person> {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        if (o == null)
            throw new RuntimeException("待比较对象为null");
        if (this.age < o.age)
            return -1;
        else if (this.age > o.age)
            return 1;
        else {
            // 年龄相同按名称
            return this.name.compareTo(o.name);
        }
    }
}
