# 一、比较器接口

Java对象默认只能对对象地址进行比较（`==`或`!=`)，若要对**对象进行排序则需要对象实现`Comparable`或`Comparator`**

## 1. Comparable自然排序

`Comparable`接口中的方法`compareTo(O)`方法用于比较两个对象的大小，`A.compareTo(B)`中若：

+ **A在B前面（小于），返回正数**
+ **A==B，返回0**
+ **A在B后面（大于），返回负数**

其中`String`和各包装类均实现类此方法，若使用数组的`Arrays.sort(O[])`或集合的`Collections.sort(C)`方法会**默认调用待排序对象的`Comparable`接口中的`compareTo()`方法**，否则会抛出异常，因此此接口也被称为**自然排序**

```java
class Person implements Comparable<Person> {
    String name;
    int age;
    @Override
    public int compareTo(Person o) {
        if (o == null)
            throw new RuntimeException("待比较对象为null");
        // 先按年龄升序
        if (this.age < o.age)
            return -1;
        else if (this.age > o.age)
            return 1;
        else {
            // 年龄相同按名称升序
            return this.name.compareTo(o.name);
        }
    }
}
```

## 2. Comparator自定义排序

`Comparator`接口用于自定义排序，当对象未实现`Comparable`接口或需要按照非对象实现的`Comparable`的接口的排序方法时，可以使用**实现`Comparator`接口的实现类，调用其`compare(A,B)`方法比较，通常都是临时使用**，其中：

+ **A在B前面（小于），返回负数**
+ **A在B后面（大于），返回正数**
+ **A和B相等，返回0**

```java
public class ComparatorSample {
    @Test
    public void comparator() {
        Student[] students = {
                new Student("lpc", 22, 98),
                new Student("hll", 23, 100),
                new Student("lyh", 21, 80),
                new Student("ll", 21, 100),
                new Student("linling", 18, 100)
        };
        System.out.println(Arrays.toString(students));
        Arrays.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                // 先比较分数 按分数降序
                if (o1.credit > o2.credit)
                    return -1;
                else if (o1.credit < o2.credit)
                    return 1;
                else
                    // 分数相同 使用Person的比较器升序
                    return o1.compareTo(o2);
            }
        });
        System.out.println(Arrays.toString(students));
    }
}

class Student extends Person {
    int credit;

    public Student(String name, int age, int credit) {
        super(name, age);
        this.credit = credit;
    }
}
```



# 二、数学和大数相关

数学和数字相关类包括`Math`数学、``

## 1. Math数学类

`Math`类中封装了和数学公式以及常用运算的**静态方法**

| 方法                          | 含义       | 描述                  |
| ----------------------------- | ---------- | --------------------- |
| abs(x)                        | 绝对值     | 取X的绝对值           |
| acos\asin\atan\cos\sin\tan(X) | 三角函数   | 求X对应的三角函数     |
| sqrt(x)                       | **平方根** | 求X的平方根           |
| pow(a,b)                      | **幂运算** | 求a的b次方=a^b        |
| log(x)                        | 十进对数   | 求以10为底x的对数=lgx |
| exp(x)                        | 自然对数   | 求以e为底x的对数=lnx  |
| max/min(a,b)                  | **最大最小** | 求ab中的最大/小值     |
|random()|**随机数**|取**[0.0,1.0)**间的随机数|
|round(x)|四舍五入|将x四舍五入取整|
|toDegrees(x)|角度|将弧度X转换为角度|
|toRadians(x)|弧度|将角度X转换为弧度|



## 2. 大数类型

`BigInterger`是大整数类，其比`long`范围还大，**支持任意长度**，具有**不可变性**

`BigDecimal`是大浮点数，可以**计算任意位数**的十进制小数，切支持任意精度，不会**丢失精度**，**不可变性**

其主要方法如下：

| 方法                    | 含义     | 描述                                     |
| ----------------------- | -------- | ---------------------------------------- |
| A.abs()                 | 绝对值   | **生成**A的绝对值的大数对象              |
| A.add(x)                | 相加     | **生成**A加上X后的对象                   |
| A.subtract(x)           | 相减     | **生成**A减去X后的对象                   |
| A.multiply(x)           | 相乘     | **生成**A乘以X后的对象                   |
| A.divide(x)             | 相除     | 生成A除以X后的对象                       |
| A.remainder(x)          | 取模     | **生成**A%X后的对象                      |
| A.divideAndRemainder(x) | 余数除法 | **生成**A除以X后的结果和余数**两个对象** |
| A.pow(x)                | 幂运算   | **生成**A^X后的对象（x为小数时是开方）   |



# 三、System系统相关类

`System`类代表系统，属于Java基础包，内部封装了众多与系统控制和属性相关的**静态方法**，该类**构造器`private`**因此无法构造。其主要方法如下：

| 方法                 | 含义         | 描述                                                         |
| -------------------- | ------------ | ------------------------------------------------------------ |
| currentTimeMillis()  | 当前时间     | 获取当前时间戳                                               |
| exit(int n)          | **结束程序** | n为**0表示正常退出**，**其他为异常退出**                     |
| gc()                 | 垃圾回收     | 告知垃圾回收线程可以进行垃圾回收（**不一定立即进行**）       |
| getProperty(Str key) | 获取属性     | 获取系统的相关属性<br>（例如java.version,os.name等获取java版本和系统名称） |

其中获取属性方法案例如下，也可以使用`getProperties()`获取全部

```java
public static void main(String[] args) {
        System.out.println(System.getProperty("java.home")); // C:\Program Files\Java\jdk1.8.0_271\jre
        System.out.println(System.getProperty("java.version")); // 1.8.0_271
        System.out.println(System.getProperty("os.name")); // Windows 10
        System.out.println(System.getProperty("os.version")); // 10.0
        System.out.println(System.getProperty("user.name")); // lpc
        System.out.println(System.getProperty("user.home")); // C:\Users\lpc
   	 	System.out.println(System.getProperties());
    }
```

