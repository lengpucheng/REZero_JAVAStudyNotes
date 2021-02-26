package cn.hll520.java.datesample;

import org.junit.Test;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-02-26-16:54
 * @since 2021-02-26-16:54
 */
public class InstantSample {
    @Test
    public void now(){
        Instant now = Instant.now();
        System.out.println(now); //2021-02-26T08:56:08.979Z
        // 时间戳构造
        Instant instant = Instant.ofEpochMilli(1614330083016L);
        System.out.println(instant);
        // 设置偏移量
        OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime); // 2021-02-26T16:58:51.981+08:00
        System.out.println(now); // 2021-02-26T08:58:51.981Z
        // 获取时间戳
        long time = now.toEpochMilli();
        System.out.println(time); // 1614330083016
    }
}
