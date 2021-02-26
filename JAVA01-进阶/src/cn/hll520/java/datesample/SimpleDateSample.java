package cn.hll520.java.datesample;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-02-26-15:02
 * @since 2021-02-26-15:02
 */
public class SimpleDateSample {
    public static void main(String[] args) {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd aa HH:mm:ss.SSSS Z");
        // 格式化
        System.out.println(format.format(new Date())); //2021-02-26 下午 15:16:31.0165 +0800
        // 解析
        String date="2021-02-26 下午 15:16:31.0165 +0800";
        try {
            System.out.println(format.parse(date).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
