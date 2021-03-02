package cn.hll520.java.collecationsample;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-03-02-14:45
 * @since 2021-03-02-14:45
 */
public class CollectionSample {

    @Test
    public void method() {
        Collection collection = new ArrayList();
        // 添加一个
        collection.add(0.1);
        collection.add("c0-2");
        Collection collection1 = new ArrayList();
        collection.add("c1-1");
        collection.add("c1-2");
        // 添加全部
        collection.addAll(collection1);
        System.out.println(collection);
        // 大小
        System.out.println(collection.size());
        // 清空
        collection.clear();
        System.out.println(collection.size());
        // 元素是否空（size==0)
        System.out.println(collection.isEmpty());
        System.out.println(collection1.isEmpty());
    }

    @Test
    public void method2(){
        Collection collection = new ArrayList();
        collection.add(1);
        collection.add(2);
        collection.add("22.33");
        collection.add(new String("str"));
        collection.add(new CoPerson(22,"lpc"));
        // 判断
        System.out.println(collection.contains("22.33"));
        System.out.println(collection.contains(new String("str")));
        System.out.println(collection.contains(new CoPerson(22, "lpc")));
        Collection collection1 = new ArrayList();
        collection1.add(2);
        collection1.add("str");
        collection1.add("22.33");
        collection1.add(new CoPerson(22,"lpc"));
        System.out.println(collection.containsAll(collection1));

        collection.remove(2);
        collection.removeAll(collection1);
        System.out.println(collection);
        collection.add(1);
        collection.add(2);
        collection.add("22.33");
        collection.add(new String("str"));
        collection.add(new CoPerson(22,"lpc"));
        // 交集
        collection.retainAll(collection1);
        System.out.println(collection);
    }
}

class CoPerson{
    int age;
    String name;

    public CoPerson(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CoPerson)) return false;
        CoPerson coPerson = (CoPerson) o;
        return age == coPerson.age && Objects.equals(name, coPerson.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }
}
