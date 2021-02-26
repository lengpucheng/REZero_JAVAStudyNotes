package cn.hll520.java.stringsample;

/**
 * 描述： 给定字符串
 * <p>将其中指定位置开始和结束的部分反转</p>
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-02-26-14:10
 * @since 2021-02-26-14:10
 */
public class StringReverse {
    public static void main(String[] args) {
        System.out.println(Reverse(8, 22, "package:cn.hll520.java:String"));
    }

    public static String Reverse(int s, int e, String str) {
        if (str == null)
            return null;
        if (e < s || s > str.length())
            return str;
        StringBuilder stringBuilder = new StringBuilder(str);
        String substring = stringBuilder.substring(s, e);
        StringBuilder reverse = new StringBuilder(substring).reverse();
        return stringBuilder.replace(s, e, reverse.toString()).toString();
    }
}
