package cn.hll520.java.iosample.nodefile;

import org.junit.Test;

import java.io.*;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-03-11-10:48
 * @since 2021-03-11-10:48
 */
public class FileInOutputStreamSample {
    File path = new File("src/res/");

    @Test
    public void callStr() throws IOException {
        // 1. 数据源
        File file=new File(path,"hello2.txt");
        // 2. 构造流
        FileInputStream inputStream = new FileInputStream(file);
        // 3. 操作
        byte[] buff=new byte[16];
        int len;
        while((len=inputStream.read(buff))!=-1)
            System.out.println(new String(buff,0,len));
        inputStream.close();
    }

    @Test
    public void outPut() throws IOException{
        // 1. 数据源
        File file=new File(path,"hello2.txt");
        // 2. 构造流
        FileOutputStream outputStream = new FileOutputStream(file,true);
        // 3. 操作
        outputStream.write(120);
        outputStream.write(new byte[]{121,76,89});
        outputStream.write(new byte[]{0,-12,127},1,2);
        outputStream.close();
    }


    @Test
    public void copyTest(){
        copy(new File(path,"hello.txt"),new File(path,"a"));
    }

    public void copy(File file,File newFile){
        // 1. 实例化数据源/目标
        FileInputStream inputStream = null;
        FileOutputStream outputStream= null;

        try {
            // 2. 流的实例化
            inputStream = new FileInputStream(file);
            outputStream = new FileOutputStream(newFile);
            // 3. 操作流
            byte[] buff=new byte[16];
            int len;
            while((len=inputStream.read(buff))!=-1)
                outputStream.write(buff,0,len);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭流
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
