package cn.hll520.java.iosample.nodefile;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-03-10-17:14
 * @since 2021-03-10-17:14
 */
public class FileReadWriterSample {
    File path = new File("src/res/");

    @Test
    public void reader() {
        FileReader fileReader = null;
        try {
            // 1. 实例化文件对象
            File file = new File(path, "hello.txt");
            // 2. 获取流对象
            fileReader = new FileReader(file);
            // 3. 从流中读取数据
            // read()返回读入的字符(char)，如果到达文件末尾返回-1
            int read;
            // 3.1 迭代
            while ((read = fileReader.read()) != -1) {
                System.out.print((char) read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null)
                try {
                    // 4. 关闭流--- 物理连接JVM不能自动关闭
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
