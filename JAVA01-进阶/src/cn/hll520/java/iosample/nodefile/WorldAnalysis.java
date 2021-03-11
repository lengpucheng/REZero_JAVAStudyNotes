package cn.hll520.java.iosample.nodefile;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：词频分析
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-03-11-15:38
 * @since 2021-03-11-15:38
 */
public class WorldAnalysis {
    @Test
    public void test() {
        analyze(new File("src/res/hello.txt"));
    }

    /**
     * 进行词频分析
     * <p>将存储在同file目录下的<b>dictionary.txt</b>中</p>
     *
     * @param file 带分析文件file
     */
    public static void analyze(File file) {
        Map<Character, Integer> dictionary = new HashMap<>();
        FileReader reader = null;
        FileWriter writer = null;
        try {
            reader = new FileReader(file);
            // 遍历写入字典
            int c;
            while ((c = reader.read()) != -1) {
                char ch = (char) c;
                dictionary.put(ch, dictionary.get(ch) == null ? 1 : dictionary.get(ch) + 1);
            }

            // 构建输出文件
            writer = new FileWriter(new File(file.getParent(), "dictionary.txt"));
            List<Map.Entry<Character, Integer>> entries = new ArrayList<>(dictionary.entrySet());
            entries.sort(Map.Entry.comparingByValue());
            for (Map.Entry<Character, Integer> entry : entries) {
                switch (entry.getKey()) {
                    case '\n':
                        writer.write("[换行] =" + entry.getValue() + "\n");
                        break;
                    case '\r':
                        writer.write("[回车] =" + entry.getValue() + "\n");
                        break;
                    case '\t':
                        writer.write("[制表] =" + entry.getValue() + "\n");
                        break;
                    default:
                        writer.write("[" + entry.getKey() + "] =" + entry.getValue() + "\n");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
