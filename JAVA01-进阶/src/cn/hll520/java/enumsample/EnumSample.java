package cn.hll520.java.enumsample;

import java.util.Arrays;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-02-27-16:05
 * @since 2021-02-27-16:05
 */
public class EnumSample {
    public static void main(String[] args) {
        System.out.println(WeekEnum.MONDAY);
        System.out.println(WeekEnum.class.getSuperclass());
        WeekEnum[] values = WeekEnum.values();
        System.out.println(Arrays.toString(values));
        WeekEnum monday = WeekEnum.valueOf("MONDAY");
        System.out.println(monday);
        try {
            System.out.println(WeekEnum.valueOf("sunday"));
            System.out.println(WeekEnum.valueOf("Monday"));
        }catch (Exception e){
            e.printStackTrace();
        }
        WeekEnum.FRIDAY.info();
    }
}



enum WeekEnum implements Info{
    // 最开始声明枚举类对象 用 ， 分割  无需声明权限和 构造
    MONDAY(1, "星期一"){
        @Override
        public void info() {
            System.out.println("当前为"+this.name()+"!?");
        }
    },
    TUESDAY(2, "星期二"){
        @Override
        public void info() {
            System.out.println("当前为"+this.name()+"!??");
        }
    },
    WEDNESDAY(3, "星期三"){
        @Override
        public void info() {
            System.out.println("当前为"+this.name()+"!!");
        }
    },
    THURSDAY(4, "星期四"){
        @Override
        public void info() {
            System.out.println("当前为"+this.name()+"!!!");
        }
    },
    FRIDAY(5, "星期五"){
        @Override
        public void info() {
            System.out.println("当前为"+this.name()+"!!!终于放假了！");
        }
    };


    private final int value;
    private final String name;

    // 1. 构造器无需声明权限
    WeekEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }
}

interface Info{
    void info();
}