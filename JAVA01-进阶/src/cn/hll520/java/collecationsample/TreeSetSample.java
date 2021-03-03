package cn.hll520.java.collecationsample;

import org.junit.Test;

import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-03-03-14:31
 * @since 2021-03-03-14:31
 */
public class TreeSetSample {

    @Test
    public void tree(){
        TreeSet set=new TreeSet();
        set.add(-2);
        set.add(-5);
        set.add(10);
        set.add(109);
        Iterator iterator = set.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());

        System.out.println("$$$$$$$$$$$$$");

        TreeSet set1=new TreeSet((o1, o2) ->
                o1==o2?0:(o1==null?-1:(o2==null?1:o1.toString().compareTo(o2.toString()))));


        set1.add(new Integer(22));
        set1.add("2dsd");
        set1.add("2ds2312");
        set1.add(25.3);
        set1.add(new Date());
        Iterator iterator1 = set1.iterator();
        while (iterator1.hasNext())
            System.out.println(iterator1.next());

    }
}
