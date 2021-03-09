package cn.hll520.java.genericsample;

import cn.hll520.java.annotionsample.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-03-09-14:47
 * @since 2021-03-09-14:47
 */
public class GenericMethod {
    public static <E> List<E> getList(E[] es) {
        return Arrays.asList(es);
    }

    @Test
    public void test(){
        System.out.println(getList(new Integer[]{1, 2, 3, 59, 99}));
        String o = get();
    }

    public static <E> E get(){
        return (E)new Object();
    }

    static class PS<T extends Person>{

    }
}
