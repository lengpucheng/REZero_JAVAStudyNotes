# 一、字符串相关

## 1. String(不可变性)

`String`代表字符串，是一个`final`修饰的**不可继承**类，其**内部是由一个`final char[]`数组存储**，因此其具有**不可变性**。

同时`String`实现类了`Serializable`接口**可序列化**以及`Comparable`接口**可比较**。

### 1.1 String实例化 

#### 1.1.1 字面量赋值

直接使用`""`对String进行赋值的字符串被称为**字面量**或**魔法值**，这种赋值方式构建的对象存储于**方法区中的字符串常量池**（JDK8）

当赋值的**字面量已经存在时会直接指向存在的常量（此时引用地址相同）**

```java
String ja="ja";
String va="va"
String str="java";
String str1="ja"+"va";
String java=ja+va;
System.out.println(str1==str);// true
System.out.println(str==java);// false
System.out.println(str==(ja+"va"));// false
System.out.println(str==java.intern());// true
```

+ 若连接运算时候**必须全部是字面量才会在常量池中**，若有任意一个为对象，都会在堆中生成。
+ 若**调用`intern()`方法**来获取对象，则该对象会**强制存储在常量池**（若已经存在会直接引用）
+ 若**声明时候用`final`修饰，依然在常量池**

#### 1.1.2 构造器实例化

使用**构造器（new)**方式实例化的字符串对象存储在堆空间，无论其内容是否相同，其地址均不相同（直接赋引用除外入，如下`str2`）,**但仍具有不可变性**。

```java
String str=new String("java");
String str1=new String(new char[]{'j','a','v','a'});
String str2=str;
```



### 1.2 不可变性

对`String`字符串进行的**任何修改（连接、`String`方法、重新赋值）都会重写开辟内存赋值产生新的对象**，**不会对原有的`String`产生任何影响**。（若是实参和形参的传递，只是改变了形参的内容，实参也不会改变）

```java
String str=new String("java");
        String str1=new String(new char[]{'j','a','v','a'});
        String str2=str;
        str=str2.substring(0, 2);
        System.out.println(str1);// java
        System.out.println(str);// ja
        System.out.println(str2);// java
```

### 1.3 常用方法

#### 1.3.1 基本方法

| 方法                    | 名称     | 描述                                    |
| ----------------------- | -------- | --------------------------------------- |
| length()                | **长度** | 获取字符串长度                          |
| charAt(int index)       | **取字符** | 获取指定index位置字符                   |
| isEmpty()               | **是否为空** | 判断数组长度是否为0，是true             |
| A.toLowerCase()         | 获取小写 | 根据A**生成**一个全小写字符串对象       |
| A.trim()                | **去除首末空格** | 根据A**生成**一个**去除首位空格**的对象 |
| concat(String s)        | 拼接     | 拼接字符串和`+`等级                     |
| A.indexOf(String s) | **首位置索引** | 在A中s第一次出现的位置**（第一个字符的下标）**,**未找到-1** |
| A.indexOf(String s,int index) | 首位置索引 | 从A中index起，s第一次出现的位置（同上） |
| A.lastIndexOf(String s) | **末位置索引** | 在A中s最后一次出现的位置（实际上**从后往前找**，同上） |
| A.lastIndexOf(String s) | 末位置索引 | 在A中index前，s最后一次出现的位置（同上） |
| valueOf(x) | **转换** | 将基本数据类型或包装类、日期时间等转换为字符串 |

#### 1.3.2 比较匹配

| 方法                              | 名称     | 描述                                                   |
| --------------------------------- | -------- | ------------------------------------------------------ |
| equals(Obj a)                     | 是否相同 | 判断内容是否相同                                       |
| equalsIgnoreCase(Str a)           | 是否相同 | **忽略大小写**判断内容是否相同                         |
| comparaTo(String s)               | 比较     | 逐个字符比较，**不同则做差**，**返回int**              |
| A.endWith(String s)               | 指定结尾 | A是否以字符串s结尾，**区分大小写**                     |
| A.startWith(String s)             | 指定开头 | A是否以字符串s开头，**区分大小写**                     |
| A.startWith(String s，int offset) | 指定匹配 | A从`offset`开始（**包含）**是否以s开头，**区分大小写** |
| A.contains(s)                     | **包含** | A中**是否包含**s字符序列，**区分大小写**               |

#### 1.3.3 截取替换生成

| 方法                             | 名称     | 描述                                                         |
| -------------------------------- | -------- | ------------------------------------------------------------ |
| A.substring(int s)               | **截取** | 从A的指定位置处截取生成字符串，**范围为[s,length]**          |
| A.substring(int s,int e)         | 截取     | 从A的指定位置处截取生成字符串，**范围为[s,e)**，**遵循左闭右开** |
| A.replace(char/str o,char/str n) | **替换** | **生成**一个将A中**所有**o**替换**成n的字符串                |
| A.replaceAll(str regex,str n)    | 替换     | **生成**一个将A中**所有匹配正则表达式`regex`**的部分替换成n的对象 |
| A.replaceFirst(str regex,str n)  | 替换     | 同上，**仅替换第一个匹配**                                   |
| A.mathces(str regex)             | **匹配** | A**是否匹配正则表达式**`regex`                               |
| A.split(str regex)               | **切分** | **根据正则表达式**`regex`将A切分生成字符串数组，**`\\.`为.** |
| A.split(str regex,int n)         | 切分     | 同上，但是切分的个数最多不超过n                              |

### 1.4 与数组的转换

#### 1.4.1 char[]

+ 调用`str.toCharArray()`可以将字符串对象str转换为`char`数组
+ 使用构造器`new String(char[])`将`char[]`数组转换为String对象

#### 1.4.2 byte

+ 调用`str.getBytes(String name)`可以将字符串对象str**以`name`字符集编码**为`byte`数组（省略时为默认）
  + 英文字母都是ASCII码
  + GBK中2个byte表示一个中文
  + UTF8默认3个byte表示一个字符
  + UTF8bm4用4个byte表示一个字符
+ 使用构造器`new String(byte[],String name)`将`byte[]`数组**以`name`字符集解码**为String对象（省略时为默认）



## 2. StringBuffer/Builder(可变)

`StringBuffer`和`StringBuilder`是具有**可变性**的字符串，两者区别：

+ `StringBuffer`：**线程安全**，全部为同步方法，但效率低下
+ `StringBuilder`：**（JDK5）**非同步方法，**线程不安全**，但效率更高

其实现属性为**`char[] value`**,为声明`final`

+ **默认初始化`char[16]`**，若初始化赋值，则**数组长度为初始化长度+16**
+ 进行拼接和替换时**当长度不足**时，则默认扩容为**原来长度的两倍+2**
+ 若拼接扩容**非常大时**长度为**原有长度+拼接长度**

以上扩容均是**新造一个`char[]`数组**,并将原有内容和拼接内容**复制进去**

### 2.1 常用方法

`StringBuffer`和`StringBuilder`在大多数方法上同`String`一致（包括不可变性），**可变**和额外声明**方法如下**：

| 方法                         | 名称     | 描述                                  |
| ---------------------------- | -------- | ------------------------------------- |
| A.append(x)                  | **添加** | 在A后拼接x**（改变原有A，以下均是）** |
| A.delete(int s,int e)        | **删除** | 删除指定位置内容，**[s,e）左闭右开**  |
| A.replace(int s,int e,str s) | **替换** | 替换**[s,e)**位置为指定字符串s        |
| A.insert(int i,x)            | **插入** | 在指定**位置i**插入**内容x**          |
| A.reverse()                  | **反转** | 将整个字符A串**顺序反转**             |
| A.setCharAt(int i,char c)    | **设置** | 将A中第i位的**字符设置为c**           |

以上方法均可以使用**方法调用链**的方式进行**链式调用**

字符相关效率从高到低依次为：`StringBuilder`>`StringBuffer`>`String`



# 二、日期时间相关

`System.currentTimeMillis()`可以返回一个`long`类型的整，为距1970年1月1日0点的**毫秒数**，被称为**时间戳**

## 1. Date

JAVA默认有两个`Date`类，分别为`java.util.Date`和`java.sql.Date`

### 1.1 util.Date

`Date`可以显示为日期和时间:

+ 使用默认**无参构造器会生成当前时间**的`Date`对象

+ 使用指定**时间戳参数构造器构造时间戳对应日期**的`Date`对象
+ `toString()`方法可以输出**默认格式的日期时间时区**

### 1.2 sql.Date

该`Date`对应数据库中的`Date`(可能只有时间或只有日期)：

+ 使用**时间戳构造器**构造时间戳指定的时间日期
+ `toString()`方法默认**仅输出日期**

使用时**间戳作为中间值**可以对两个`Date`进行**相互转换**



### 1.3 SimpleDateFormat(格式化)

`SimpleDateFormat`可以将**日期格式化为日期字符串**，同时也可以把日期字符串转换为日期对象，主要有以下格式构造器：

+ `SimpleDateFormat()`：使用默认构造
+ `SimpleDateFormat(String pattern)`：**使用`pattern`**对应的日期格式字符串**作为格式化和解析格式**

#### 1.3.1 格式字符串 

| 字符 | 含义 | 用法 |
| ---- | ---- | ---- |
| y    | 年   | yyyy |
| M    | 月   | MM   |
| d    | 日   | dd   |
| h    | 时   | hh   |
| m    | 分   | mm   |
| s    | 秒   | ss   |
| a    | 半天   | aa   |
| S    | 毫秒   | SSS   |
| Z    | 时区   | Z或z   |
| w    | 一年中的周   | w  |
| W    | 一月中的周   | w  |

#### 1.3.2 格式化/解析日期

+ 调用`SimpleDateFormat`实例对象的**`format()`**方法可以将日期进行**格式化**。

+ 调用`SimpleDateFormat`实例对象的**`parse()`方法可以日期字符串进行解析**，需要处理异常。

````java
 public static void main(String[] args) {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd aa HH:mm:ss.SSSS Z");
        // 格式化
        System.out.println(format.format(new Date())); //2021-02-26 下午 15:16:31.0165 +0800
        // 解析
        String date="2021-02-26 下午 15:16:31.0165 +0800";
        try {
            System.out.println(format.parse(date).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
````



## 2. Calender(日历)

`Calender`是日历的**抽象类**，封装了和日历相关的方法，一般使用其子类`GregorianCalendar`实例化，或者使用其**静态方法`Calender.getInstance()`获取实例化对象**

### 2.1 常用方法

| 方法              | 含义     | 描述                                                 |
| ----------------- | -------- | ---------------------------------------------------- |
| get(Calender.X)   | 获取     | 获取X对应的，X为Calender中的静态变量（包含年月日等） |
| set(Calender.X,n) | 设置     | 设置X对应的属性为n                                   |
| add(Calender.x,n) | 添加     | 在当前日历的**属性X上加上n**（可以为负数）           |
| getTime()         | 获取日期 | 将日历**转换为Date对象**                             |
| setTime(d)        | 设置日期 | 将当前日历**设置**为Date对象d表示的**时间**          |

`Calender`日历中**月份从一月（0）开始**，**星期从周日（1）开始**



## 3. LocalDate/Time(不可变JDK8)

`LocalDateTime`是JDK8新引入的一系列日期时间API，具有**不可变性**，**无偏移性**，**可格式化**等特性。

### 3.1 实例化对象

调用**静态方法`now()`**可以获取本地日期的**当前时间对象**

```java
 public void Now() {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDate);// 2021-02-26
        System.out.println(localTime);// 16:36:12.710
        System.out.println(localDateTime);// 2021-02-26T16:36:12.710
    }
```

使用**静态方法`of()`**可以获取**指定日期时间**的的对象（**无偏移量**）

```java
public void Of(){
        // 2021-06-06
        System.out.println(LocalDate.of(2021, 6, 6));
        // 05:20:13.000000014
        System.out.println(LocalTime.of(5, 20, 13, 14));
        // 2020-06-06T05:20:13.000000014
        System.out.println(LocalDateTime.of(2020, 6, 6, 5, 20, 13, 14));
    }
```

### 3.2 常用方法

| 方法       | 含义  | 描述                                                         |
| ---------- | ----- | ------------------------------------------------------------ |
| A.get(X)   | 获取X | 获取**X枚举**对应的值（年月日周等）                          |
| A.getX()   | 获取X | 获取**X对应**的值（同上）                                    |
| A.withX(n) | 设置X | **生成**一个在A基础上**设置X为n**的日期时间对象**（不可变性）** |
| A.plusX(n) | 添加X | **生成**一个在A基础上**X添加n**的日期时间对象**（不可变性，会自动进位，n可负）** |




## 4. Instant(瞬间JDK8)

`Instant`表示一个瞬间时间戳，可以精确到**纳秒**，其默认**参照标准为UTC+0时间**

+ 调用静态方法`now()`可以获取在**当前UTC+0的`Instant`**
+ 调用静态方法`ofEpochMilli(n)`可以获取在**当前UTC+0下时间戳N的`Instant`**
+ 可以调用**实例对象**的`atOffset()`方法，**设置相应的偏移量**，获取偏移后的`OffsetDateTime`**（不可变性）**
+ 可以调用**实例对象**的`toEpochMilli()`方法，获取当前`Instant`对应的**时间戳**

```java
public void now(){
        Instant now = Instant.now();
        System.out.println(now); //2021-02-26T08:56:08.979Z
        // 时间戳构造
        Instant instant = Instant.ofEpochMilli(1614330083016L);
        System.out.println(instant);
        // 设置偏移量
        OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime); // 2021-02-26T16:58:51.981+08:00
        System.out.println(now); // 2021-02-26T08:58:51.981Z
        // 获取时间戳
        long time = now.toEpochMilli();
        System.out.println(time); // 1614330083016
    }
```



## 5. DateTimeFormatter(JDK8)

`DateTimeFormatter`同`SimpleDateFormat`类似，是JDK8新日期时间API的**格式化类**，主要有三种实例化模式：

+ `DateTimeFormatter.X`：**X是**定义好的默认格式化格式**常量**，用来处理**标准的格式化和解析**（机器码）
+ `ofLocalizedDateTime(X)`：构造一个适配`DateTime`(也可以是其他)的**默认**X的**格式化**（本地语言）
+ `ofPattern(str)`：构造一个**自定义格式str的格式化对象（同`SimpleDateFormat`类似）**

```java
@Test
    public void diy(){
        LocalDateTime dateTime = LocalDateTime.of(2020, 6, 6,
                5, 20, 13, 14);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh:mm:ss");
        System.out.println(formatter.format(dateTime));
        TemporalAccessor parse = formatter.parse("2020年06月06日 05:20:13");
        //{HourOfAmPm=5, MicroOfSecond=0, SecondOfMinute=13, MilliOfSecond=0, MinuteOfHour=20, NanoOfSecond=0},ISO resolved to 2020-06-06
        System.out.println(parse);
    }
```

**解析和格式化如上，同`SimpleDateFormat`用法类似**



