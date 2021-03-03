package cn.hll520.java.collecationsample;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-03-03-21:28
 * @since 2021-03-03-21:28
 */
public class CollectionsSample {
    @Test
    public void reverse(){
        List list=new ArrayList();
        list.add(996);
        list.add(566);
        list.add(7);
        list.add(225);
        System.out.println(list);
        Collections.reverse(list);
        System.out.println(list);

        Collections.shuffle(list);
        System.out.println(list);

        Collections.sort(list);
        System.out.println(list);
    }

    @Test
    public void test(){
        List list=new ArrayList();
        list.add(996);
        list.add(566);
        list.add(7);
        list.add(225);
        list.add(225);
        list.add(0);
        list.add(7);

        System.out.println(Collections.frequency(list,225));
        System.out.println(Collections.frequency(list,-1));
        System.out.println(Collections.frequency(list,0));
    }

    @Test
    public void copy(){
        List src=new ArrayList();
        src.add(996);
        src.add(566);
        src.add(7);
        src.add(225);
        src.add(225);
        src.add(0);
        src.add(7);

        List dest=Arrays.asList(new Object[src.size()]);
        Collections.copy(dest,src);

        System.out.println(src);
        System.out.println(dest);
        System.out.println(dest.get(2));
        System.out.println(dest instanceof List);
        src.remove(2);
        System.out.println(dest);
        System.out.println(src);
    }
}
