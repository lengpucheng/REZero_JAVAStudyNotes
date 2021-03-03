package cn.hll520.java.mapsample;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-03-03-17:32
 * @since 2021-03-03-17:32
 */
public class PropertiesSample {

    @Test
    public void sample() throws Exception {
        // 读取文件流
        FileInputStream fileInputStream = new FileInputStream("map.properties");
        Properties properties = new Properties();

        // 加载流对象
        properties.load(fileInputStream);

        // 获取
        String name = properties.getProperty("name");
        System.out.println(name); // lpc
        // 获取不存在
        System.out.println(properties.getProperty("name2")); // null
        // 获取并设置默认值
        System.out.println(properties.getProperty("name2","lengpucheng")); // lengpucheng
        System.out.println(properties.getProperty("name","lengpucheng")); // lpc

        // 关闭文件流
        fileInputStream.close();
    }
}
