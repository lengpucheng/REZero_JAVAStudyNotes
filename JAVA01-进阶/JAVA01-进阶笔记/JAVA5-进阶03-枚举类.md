# 枚举类Enum

若类的对象只要**有限确定**个，可以将类的所有对象全部声明为常量，即为**枚举类**

+ `JDK5`之前通常使用**私有化构造器**，声明**静态常量**方式来构建枚举类
+ `JDK5`之后，使用**`enum`来声明类枚举类**

## 1. 创建枚举类

+ 使用`enum`来声明枚举类
+ 在类中最开始声明需要枚举的对象，**无需声明权限以及显示new**，**使用`,`分隔对象**
+ `enum`默认继承**父类为`java.lang.Enum`**

```java
enum WeekEnum {
    // 最开始声明枚举类对象 用 ， 分割  无需声明权限和 构造
    MONDAY(1, "星期一"),
    TUESDAY(2, "星期二"),
    WEDNESDAY(3, "星期三"),
    THURSDAY(4, "星期四"),
    FRIDAY(5, "星期五");


    private final int value;
    private final String name;

    // 1. 构造器无需声明权限
    WeekEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
```

**枚举类输出打印时，默认是名称**



## 2. 枚举类常用方法

| 方法           | 含义 | 描述                                                         |
| -------------- | ---- | ------------------------------------------------------------ |
| A.values()     | 值组 | 输出枚举类A的**全部对象组成的数组**                          |
| A.valueOf(n)   | 获取 | 获取枚举类A中**名称为n的对象**，若不存在或大小写错误将**抛出异常** |
| A.comparaTo(b) | 比较 | 枚举类默认实现了`Comparable`接口可以进行比较                 |

特别的**枚举了可以使用`switch`进行分支**



## 3. 枚举对象定制接口

当枚举类实现接口时候若**在类中实现接口方法则所有对象该方法均一致**，因此可以**使用`内部类`的方式在给每个对象复写接口方法**

```java
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
```
