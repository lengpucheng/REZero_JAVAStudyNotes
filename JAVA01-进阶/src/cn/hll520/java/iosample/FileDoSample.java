package cn.hll520.java.iosample;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-03-10-15:30
 * @since 2021-03-10-15:30
 */
public class FileDoSample {

    @Test
    public void create() throws IOException {
        File file = new File("./src/res/");
        // 创建文件
        System.out.println(new File(file,"do.txt").createNewFile());
        // 创建文件目录 若父目录不存在 则不执行
        System.out.println(new File(file,"abc/ppg").mkdir());
        // 创建文件目录 若父目录不存在 则一并创建
        System.out.println(new File(file,"do/does/did").mkdirs());
        // 删除文件
        System.out.println(new File(file, "do.txt").delete());
    }


    @Test
    public void sample() throws IOException {
        File file = new File("./src/res/file/sample");
        File fileF=new File(file,"sample.txt");
        if(!file.isDirectory()||!file.exists()){
            System.out.println(file.mkdirs());
        }
        if(!fileF.exists()){
            System.out.println(fileF.createNewFile());
        }else {
            System.out.println(fileF.delete());
            System.out.println(file.delete());
        }
    }
}
