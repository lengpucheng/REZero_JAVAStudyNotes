package cn.hll520.java.datesample;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-02-26-17:06
 * @since 2021-02-26-17:06
 */
public class DateTimeFormatterSample {
    @Test
    public void sample() {
        LocalDateTime dateTime = LocalDateTime.of(2020, 6, 6,
                5, 20, 13, 14);
        // 默认
        DateTimeFormatter formatter=DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        String str= formatter.format(dateTime);
        System.out.println(str); // 2020-06-06T05:20:13.000000014
        LocalDateTime parse = LocalDateTime.from(formatter.parse(str));
    }

    @Test
    public void local(){
        LocalDateTime dateTime = LocalDateTime.of(2020, 6, 6,
                5, 20, 13, 14);
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        System.out.println(formatter.format(dateTime)); // 2020年6月6日 上午05时20分13秒
    }

    @Test
    public void diy(){
        LocalDateTime dateTime = LocalDateTime.of(2020, 6, 6,
                5, 20, 13, 14);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh:mm:ss");
        System.out.println(formatter.format(dateTime));
        TemporalAccessor parse = formatter.parse("2020年06月06日 05:20:13");
        //{HourOfAmPm=5, MicroOfSecond=0, SecondOfMinute=13, MilliOfSecond=0, MinuteOfHour=20, NanoOfSecond=0},ISO resolved to 2020-06-06
        System.out.println(parse);
    }
}
