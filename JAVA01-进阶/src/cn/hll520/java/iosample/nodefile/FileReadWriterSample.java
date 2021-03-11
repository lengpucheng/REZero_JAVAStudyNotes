package cn.hll520.java.iosample.nodefile;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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

    @Test
    public void writer() {
        // 1. 实例化数据目标
        File file = new File(path, "hello2.txt");
        FileWriter fileWriter = null;
        try {
            // 2. 实例化流
            fileWriter = new FileWriter(file, true);
            // 3. 写入的时候
            // 若文件不存在则创建
            // 若文件存在则直接覆盖（非尾部写入）
            fileWriter.write("\n1234567890", 3, 5);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭流
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void copyFile() {
        // 1. 数据源/目标实例化
        File file = new File(path, "hello2.txt");
        File newFile = new File(path, "helloCopy.txt");

        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            // 2. 实例化 数据流
            fileReader = new FileReader(file);
            fileWriter = new FileWriter(newFile);

            // 3. 流操作
            char[] buff = new char[16]; // 3.1 每次读取大小
            int len;
            // 3.2 获取读取长度 并判断是否到尾部 -1
            while ((len = fileReader.read(buff)) != -1) {
                // 3.3 写入读取的长度
                fileWriter.write(buff, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭流
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
