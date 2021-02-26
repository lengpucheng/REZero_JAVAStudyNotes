package cn.hll520.java.stringsample;

/**
 * 描述：字符串中某一子串出现的次数
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-02-26-14:24
 * @since 2021-02-26-14:24
 */
public class StringArise {
    public static void main(String[] args) {
        System.out.println(AriseCount("ajavajavajavajbjavajaaabccjavajeabcdbccaab", "javaj"));
    }

    public static int AriseCount(String str, String subStr) {
        if(str==null||subStr==null||subStr.length()>str.length())
            return 0;
        StringBuilder stringBuilder = new StringBuilder(str);
        int count = 0;
        int index = -1;
        while (true) {
            index = stringBuilder.indexOf(subStr, index + 1);
            if (index >= 0)
                count++;
            else if (index == -1 || index >= stringBuilder.length() - 1)
                return count;
        }
    }
}
