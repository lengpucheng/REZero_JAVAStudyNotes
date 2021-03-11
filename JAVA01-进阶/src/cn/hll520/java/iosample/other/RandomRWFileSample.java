package cn.hll520.java.iosample.other;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-03-11-20:53
 * @since 2021-03-11-20:53
 */
public class RandomRWFileSample {

    @Test
    public void test() throws IOException {
        RandomAccessFile accessFileR = new RandomAccessFile("src/res/lpc.asc", "r");
        RandomAccessFile accessFileW = new RandomAccessFile("src/res/lpc2.asc", "rw");

        int len;
        byte[] bytes = new byte[1024];
        while ((len = accessFileR.read(bytes)) != -1)
            accessFileW.write(bytes, 0, len);
        accessFileR.close();
        accessFileW.close();
    }

    @Test
    public void peek() throws IOException {
        File file = new File("src/res/lpc2.asc");
        RandomAccessFile accessFileR = new RandomAccessFile(file, "rw");
        accessFileR.seek(file.length());
        accessFileR.writeUTF("\nLPC HELLO!");
        accessFileR.close();
    }

    @Test
    public void midAddTest() throws IOException {
        midAdd(new File("src/res/lpc2.asc"),40,
                new String[]{"\n","299","lpc\n","25544"});
    }

    public void midAdd(File file, long pos, Object... value) throws IOException {
        // 实例化对象
        RandomAccessFile access = new RandomAccessFile(file, "rw");
        // 移动到插入位置
        access.seek(pos);
        // 缓存插入位置后的数据
        StringBuilder builder = new StringBuilder((int) file.length());
        int len;
        byte[] bytes = new byte[1024];
        while ((len = access.read(bytes)) != -1) {
            builder.append(new String(bytes, 0, len));
        }
        // 将指针重新回到插入位置
        access.seek(pos);
        // 写入数据
        if (value != null) {
            for (Object obj : value)
                access.writeBytes(obj.toString());
        }
        // 将缓存的数据还原回插入点后
        access.writeBytes(builder.toString());

        // 关闭流
        access.close();

    }
}
