package cn.hll520.java.datesample;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-02-26-15:55
 * @since 2021-02-26-15:55
 */
public class CalenderSample {
    public static void main(String[] args) {
        Calendar instance = Calendar.getInstance();
        System.out.println(instance.get(Calendar.MONTH));
        System.out.println(instance.get(Calendar.YEAR));
        instance.set(Calendar.YEAR,2019);
        System.out.println(instance.get(Calendar.YEAR));
        System.out.println(instance.getTime());
        instance.setTime(new Date());
        System.out.println(instance);
    }
}
