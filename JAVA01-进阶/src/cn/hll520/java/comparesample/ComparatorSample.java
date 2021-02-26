package cn.hll520.java.comparesample;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-02-26-22:40
 * @since 2021-02-26-22:40
 */
public class ComparatorSample {
    @Test
    public void comparator() {
        Student[] students = {
                new Student("lpc", 22, 98),
                new Student("hll", 23, 100),
                new Student("lyh", 21, 80),
                new Student("ll", 21, 100),
                new Student("linling", 18, 100)
        };
        System.out.println(Arrays.toString(students));
        Arrays.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                // 先比较分数 高的在前
                if (o1.credit > o2.credit)
                    return -1;
                else if (o1.credit < o2.credit)
                    return 1;
                else
                    // 分数相同比使用Person的比较器
                    return o1.compareTo(o2);
            }
        });
        System.out.println(Arrays.toString(students));
    }
}

class Student extends Person {
    int credit;

    public Student(String name, int age, int credit) {
        super(name, age);
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "Student{" +
                "credit=" + credit +
                "} " + super.toString();
    }
}
