package cn.hll520.java.iosample;

import org.junit.Test;

import java.io.File;
import java.util.Arrays;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-03-09-16:07
 * @since 2021-03-09-16:07
 */
public class FileSample {
    @Test
    public void fileTest(){
        // 构造
        File file=new File("../Readme.md");
        File fileA=new File("C:\\ProjectSpace\\从零开始的JAVA学习笔记\\Readme.md");
        File fileO=new File("../","Readme.me");
        System.out.println(file);
        System.out.println(fileA);
        System.out.println(fileO);
        System.out.println("-----------------------------");
        // 绝对路径
        System.out.println("file:-->"+file.getAbsolutePath());
        System.out.println("fileA:-->"+fileA.getAbsolutePath());
        System.out.println("fileO:-->"+fileO.getAbsolutePath());
        // 相对路径
        System.out.println("file:-->"+file.getPath());
        System.out.println("fileA:-->"+fileA.getPath());
        System.out.println("fileO:-->"+fileO.getPath());
        // 文件名
        System.out.println("file:-->"+file.getName());
        System.out.println("fileA:-->"+fileA.getName());
        System.out.println("fileO:-->"+fileO.getName());
        // 父路径
        System.out.println("file:-->"+file.getParent());
        System.out.println("fileA:-->"+fileA.getParent());
        System.out.println("fileO:-->"+fileO.getParent());
        // 大小
        System.out.println("file:-->"+file.length());
        // 最后修改时间
        System.out.println("file:-->"+file.lastModified());
        // 目录下（null)
        System.out.println(Arrays.toString(file.list()));
    }

    @Test
    public void directory(){
        File dir=new File("JAVA01-进阶笔记");
        // 目录下名称
        String[] list = dir.list();
        System.out.println(Arrays.toString(list));
        System.out.println("--------------------");
        // 文件对象
        File[] files = dir.listFiles();
        System.out.println(Arrays.toString(files));
    }

    @Test
    public void reName(){
        File fileO =new File("src/res/hello.txt");
        File fileN = new File("src/res/helloWorld.txt");
//        System.out.println(fileO.renameTo(fileN));
        System.out.println(fileN.renameTo(fileO));

        System.out.println(new File("/").getAbsolutePath());
    }

    @Test
    public void judge(){
        File file=new File("../Readme.md");
        // 是否存在
        System.out.println(file.exists());
        // 是否隐藏
        System.out.println(file.isHidden());
        // 判断是否是文件
        System.out.println(file.isFile());
        // 是否是目录
        System.out.println(file.isDirectory());
        // 是否可读
        System.out.println(file.canRead());
        // 是否可写
        System.out.println(file.canWrite());
        // 是否可执行
        System.out.println(file.canExecute());
    }


}
