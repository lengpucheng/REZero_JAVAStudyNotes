package cn.hll520.java.datesample;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-02-26-16:13
 * @since 2021-02-26-16:13
 */
public class LocalTimeSample {
    @Test
    public void Now() {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);
    }

    @Test
    public void Of(){
        // 2021-06-06
        System.out.println(LocalDate.of(2021, 6, 6));
        // 05:20:13.000000014
        System.out.println(LocalTime.of(5, 20, 13, 14));
        // 2020-06-06T05:20:13.000000014
        System.out.println(LocalDateTime.of(2020, 6, 6, 5, 20, 13, 14));
    }

    @Test
    public void get(){
        LocalDateTime date = LocalDateTime.of(2021,6,6,5,20,10);
        System.out.println(date.get(ChronoField.YEAR)); //2021
        System.out.println(date.getYear()); // 2021
        System.out.println(date.getDayOfWeek()); // SUNDAY
        System.out.println(date.getMonthValue()); //6
        System.out.println(date.getMonth()); // JUNE
        System.out.println(date.getSecond()); // 10
    }

    @Test
    public void set(){
        LocalDateTime date = LocalDateTime.of(2021,6,6,5,20,10);
        LocalDateTime dateTime = date.withYear(2020);
        System.out.println(dateTime.getYear()); // 2020
        System.out.println(date.getYear()); // 2021
    }
    @Test
    public void add(){
        LocalDateTime date = LocalDateTime.of(2021,6,6,5,20,10);
        LocalDateTime dateTime = date.plusMonths(12);
        System.out.println(dateTime); // 2022-06-06T05:20:10
        System.out.println(date.plusMonths(-12)); //2020-06-06T05:20:10
        System.out.println(date); // 2021-06-06T05:20:10
    }
}
