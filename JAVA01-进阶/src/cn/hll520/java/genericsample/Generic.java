package cn.hll520.java.genericsample;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-03-09-14:22
 * @since 2021-03-09-14:22
 */
public class Generic {

    @Test
    public void test(){
        List<String> list=new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());


        for (String s : list) System.out.println(s);
    }

    static class Student<L> extends Person<L>{

    }

    static class Person<L>{
        String name;
        int age;
        L language;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public L getLanguage() {
            return language;
        }

        public void setLanguage(L language) {
            this.language = language;
        }
    }
}

