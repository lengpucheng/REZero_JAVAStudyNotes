package cn.hll520.java.iosample.nodefile;

import org.junit.Test;

import java.io.*;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-03-11-15:25
 * @since 2021-03-11-15:25
 */
public class FileSecret {

    File path=new File("src/res/");
    @Test
    public void test(){
//        encrypt(new File(path,"lpc.asc"),new File(path,"lpcSec.asc"));
        encrypt(new File(path,"lpcSec.asc"),new File(path,"lpcSecRe.asc"));
    }

    /**
     * 加密和解密
     * @param file 需加密/解密文件
     * @param newFile 加密/解密后文件
     */
    public static void encrypt(File file, File newFile) {
        BufferedInputStream inputStream = null;
        BufferedOutputStream outputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            outputStream = new BufferedOutputStream(new FileOutputStream(newFile));

            byte[] buff = new byte[1024];
            int len;
            while ((len = inputStream.read(buff)) != -1) {
                // 异或进行加密
                for (int i = 0; i < len; i++) {
                    // (C^A)^A=C   C^A=B
                    buff[i] = (byte) (buff[i] ^ 5);
                }
                outputStream.write(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
