package cn.hll520.java.stringsample;

/**
 * 描述：唯一最大相同字串
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-02-26-14:33
 * @since 2021-02-26-14:33
 */
public class StringMaxSubStr {
    public static void main(String[] args) {
        System.out.println(maxSubStr("love.hll520.cn", "cn.hll520.java"));
    }

    public static String maxSubStr(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return null;
        }
        // 取最大和小
        String maxStr = str1.length() >= str2.length() ? str1 : str2;
        String minStr = str1.length() < str2.length() ? str1 : str2;
        int subSize = minStr.length();

        // 先从小串的长度开始取，每次往后减一位
        for (int i = 0; i < subSize; i++) {
            // 分别取开头和最后位-i位(中开头到中间)， 之后每次往后滑动一位
            for (int s = 0, e = subSize - i; e <= subSize; s++, e++) {
                // 如果长的包含短的 则最大就是短串
                String substring = minStr.substring(s, e);
                if (maxStr.contains(substring)) {
                    return substring;
                }
            }
        }

        return null;
    }
}
