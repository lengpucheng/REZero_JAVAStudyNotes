package cn.hll520.java.iosample.nodefile;

import org.junit.Test;

import java.io.*;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-03-11-14:41
 * @since 2021-03-11-14:41
 */
public class BufferedSample {
    File path = new File("src/res/");

    @Test
    public void bufferedCopy() {
        copy(new File(path,"hello.txt"),new File(path,"hello2.txt"));
    }

    // 1. 实例化数据源/目标
    public void copy(File file, File newFile) {

        FileInputStream inputStream;
        FileOutputStream outputStream;
        BufferedInputStream bin = null;
        BufferedOutputStream bout = null;
        try {
            // 2. 流的实例化
            inputStream = new FileInputStream(file);
            outputStream = new FileOutputStream(newFile);
            //2.1 使用节点流来实例化处理流
            bin = new BufferedInputStream(inputStream);
            bout = new BufferedOutputStream(outputStream);

            // 3. 操作流 操作处理流
            byte[] buff = new byte[16];
            int len;
            while ((len = bin.read(buff)) != -1)
                bout.write(buff, 0, len);



        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4.1 关闭处理流
            try {
                if (bin != null) {
                    bin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bout != null) {
                    bout.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 4.2 关闭流 关闭处理流将自动关闭节点流
//            try {
//                if (inputStream != null) {
//                    inputStream.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                if (outputStream != null) {
//                    outputStream.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

    @Test
    public void copyStrTest(){
        copyStr(new File(path,"lpc.asc"),new File(path,"hello.txt"));
    }

    public void copyStr(File file, File newFile){
        BufferedReader reader= null;
        BufferedWriter writer= null;
        try {
            // 2. 实例化话流
            reader = new BufferedReader(new FileReader(file));
            writer = new BufferedWriter(new FileWriter(newFile));

            // 3. 操作
            String line;
            while((line=reader.readLine())!=null){
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4.关闭流
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
