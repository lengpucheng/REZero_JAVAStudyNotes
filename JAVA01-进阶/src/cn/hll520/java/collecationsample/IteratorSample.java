package cn.hll520.java.collecationsample;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-03-02-15:29
 * @since 2021-03-02-15:29
 */
public class IteratorSample {

    @Test
    public void iterator() {
        Collection collection = new ArrayList();
        collection.add(1);
        collection.add(2);
        collection.add("22.33");
        collection.add(new String("str"));
        collection.add(new CoPerson(22, "lpc"));

        Iterator iterator = collection.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());

        for(Object o:collection){
            System.out.println(o);
        }
        Object[] objects = collection.toArray();
        for(Object o:objects){
            System.out.println(o);
        }
    }

    @Test
    public void list(){
        List list=new ArrayList();
        list.add(3);
        list.add(2);
        list.add(1);

        System.out.println(list);

        Object remove = list.remove(new Integer(2));
        System.out.println(remove);
        System.out.println(list);
    }

}
