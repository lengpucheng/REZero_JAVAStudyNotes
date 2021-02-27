package cn.hll520.java.enumsample;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-02-27-15:51
 * @since 2021-02-27-15:51
 */
public class WeekSample {
    public static void main(String[] args) {
        System.out.println(Week.FRIDAY);
        System.out.println(Week.MONDAY);

    }
}

// 自定义枚举
class Week{
    private final int value;
    private final String name;

    public static final Week MONDAY=new Week(1,"星期一");
    public static final Week TUESDAY=new Week(2,"星期二");
    public static final Week WEDNESDAY=new Week(3,"星期三");
    public static final Week THURSDAY=new Week(4,"星期四");
    public static final Week FRIDAY=new Week(5,"星期五");

    // 1. 私有化构造器
    private Week(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Week{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }
}
