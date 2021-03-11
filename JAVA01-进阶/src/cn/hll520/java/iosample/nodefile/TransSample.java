package cn.hll520.java.iosample.nodefile;

import org.junit.Test;

import java.io.*;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-03-11-16:22
 * @since 2021-03-11-16:22
 */
public class TransSample {
    @Test
    public void test() throws IOException {
        FileInputStream inputStream = new FileInputStream("src/res/hello.txt");
        // 无参数使用默认字符集解码
        // 参数二指定字符集
        InputStreamReader reader=new InputStreamReader(inputStream);
        int len;
        char[] chars=new char[1024];
        while ((len=reader.read(chars))!=-1){
            System.out.println(new String(chars,0,len));
        }
        reader.close();
    }

    @Test
    public void tranTest() throws IOException {
        File file=new File("src/res/dictionary.txt");
        File newFile=new File("src/res/dictionary_new.txt");
        tranType(file,"utf-8",newFile,"BIG5");
    }

    public void tranType(File file,String type,File newFile,String newType)throws IOException{
        InputStreamReader reader=new InputStreamReader(new FileInputStream(file),type);
        OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream(newFile),newType);

        char[] chars=new char[1024];
        int len;
        while ((len=reader.read(chars))!=-1)
            writer.write(chars,0,len);


        writer.close();
        reader.close();
    }

}
