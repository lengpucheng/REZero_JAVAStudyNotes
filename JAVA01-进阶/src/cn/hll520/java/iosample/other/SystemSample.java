package cn.hll520.java.iosample.other;

import org.junit.Test;

import java.io.*;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-03-11-19:25
 * @since 2021-03-11-19:25
 */
public class SystemSample {
    public static void main(String[] args) {
        in();
    }


    public static void in() {
        BufferedReader bufferedReader = null;
        try {
            InputStreamReader reader = new InputStreamReader(System.in);
            bufferedReader = new BufferedReader(reader);
            while (true) {
                String s = bufferedReader.readLine();
                if ("e".equalsIgnoreCase(s) || "exit".equalsIgnoreCase(s))
                    return;
                System.out.println(s.toUpperCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
