package cn.hll520.java.comparesample;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-02-26-23:02
 * @since 2021-02-26-23:02
 */
public class SystemSample {
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.home")); // C:\Program Files\Java\jdk1.8.0_271\jre
        System.out.println(System.getProperty("java.version")); // 1.8.0_271
        System.out.println(System.getProperty("os.name")); // Windows 10
        System.out.println(System.getProperty("os.version")); // 10.0
        System.out.println(System.getProperty("user.name")); // lpc
        System.out.println(System.getProperty("user.home")); // C:\Users\lpc
        System.out.println(System.getProperties());
    }

}
