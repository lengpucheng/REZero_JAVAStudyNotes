package cn.hll520.java.iosample;

import org.junit.Test;

import java.io.File;

/**
 * 描述： 虽然文件或目录
 * name|
 * |name size
 * |dirName
 * ----|name2 size
 * |dirName2
 * |name size
 * |name3 size
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-03-10-15:55
 * @since 2021-03-10-15:55
 */
public class FileTree {


    @Test
    public void tree() {
        fileTree(new File("../"));
    }

    public static void fileTree(File file) {
        printFileTree(0, 0, file);
        System.out.println("******************");
        System.out.println("共" + fileCount + "个文件");
        System.out.println("共" + dirCount + "个目录");
        System.out.println("总大小:" + ((size / 1024.0) / 1024) + " mb");
    }

    private static long size;
    private static int fileCount;
    private static int dirCount;

    private static void printFileTree(int bef, int level, File... files) {
        if (files == null)
            return;
        for (File file : files) {
            // 不存在就返回
            if (!file.exists()) {
                return;
            }
            if (file.isFile()) {
                // 单个文件打印
                System.out.println(level(bef, level) + " " + file.getName() + "  " + file.length());
                fileCount++;
                size += file.length();
            } else if (file.isDirectory()) {
                // 文件夹打印信息后递归
                dirCount++;
                System.out.println(level(bef, level) + file.getName() + "|:");
                printFileTree(level, 4 + level, file.listFiles());
            }
        }

    }

    /**
     * 层级符
     */
    private static String level(int bef, int aft) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < aft; i++) {
            if (i < bef)
                str.append(" ");
            else if (i == bef)
                str.append("|");
            else
                str.append("-");
        }
        return str.toString();
    }

}
