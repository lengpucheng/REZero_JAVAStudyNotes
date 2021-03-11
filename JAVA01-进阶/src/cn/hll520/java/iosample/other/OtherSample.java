package cn.hll520.java.iosample.other;

import org.junit.Test;

import java.io.*;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-03-11-19:58
 * @since 2021-03-11-19:58
 */
public class OtherSample {
    @Test
    public void test() throws IOException {

        PrintStream printStream = new PrintStream(new FileOutputStream("src/res/print.txt"), true);
        System.setOut(printStream);
        for(int i=0;i<255;i++){
            System.out.println((char)i);
        }
        printStream.close();
    }
    @Test
    public void date() throws IOException {
        // 写人
        DataOutputStream outputStream=new DataOutputStream(new FileOutputStream("src/res/data.io"));
        outputStream.writeInt(9866);
        outputStream.flush();// 手动刷新(立即写入)
        outputStream.writeBoolean(true);
        outputStream.flush();// 手动刷新(立即写入)
        outputStream.writeUTF("java");
        outputStream.flush();// 手动刷新(立即写入)
        outputStream.writeDouble(1999.0125);
        outputStream.flush();// 手动刷新(立即写入)
        outputStream.close();
        // 读取
        DataInputStream inputStream=new DataInputStream(new FileInputStream("src/res/data.io"));
        System.out.println(inputStream.readInt());
        System.out.println(inputStream.readBoolean());
        System.out.println(inputStream.readUTF());
        System.out.println(inputStream.readDouble());
        inputStream.close();
    }
}
