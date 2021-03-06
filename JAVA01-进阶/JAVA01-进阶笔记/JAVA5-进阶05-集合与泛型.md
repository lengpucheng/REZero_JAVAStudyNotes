集合作为数组的一种替换对象，可以像数组一样对多个对象和数据进行操作，其可以**动态开辟内存空间**，对数据进行一些检验，并提供更多的常用方法。（在**内存中数组是连续的内存空间**，并且具有顺序性）



# 一、集合接口

在JAVA中集合有两套**接口**体系`Collection`和`Map`：

+ `Collection`：**一组数据的容器**，定义了存取一组对象的方法
  + `List`：**有序、可重复**数据集，类似于**动态数组**
  + `Set`：**无序、不可重复**数据集
+ `Map`：**双列数据的容器**，保存具有映射关系的`key-value`键值对

## 1. Collection体系

![image-20210302142527448](C:\Users\lpc\Desktop\笔记\JAVA01-进阶\images\image-20210302142527448.png)

## 2. Map体系

![image-20210302142549630](C:\Users\lpc\Desktop\笔记\JAVA01-进阶\images\image-20210302142549630.png)



# 二、Collection接口

## 1. 通用方法

| 方法               | 含义       | 描述                                                         |
| ------------------ | ---------- | ------------------------------------------------------------ |
| A.add(O o)         | **添加**   | 向集合A中添加一个元素o，成功返回true                         |
| A.addAll(C c)      | 添加集合   | 向集合A中添加**集合c**中**全部元素**，成功返回true           |
| A.clear()          | 清空       | 清空集合A中全部元素                                          |
| A.size()           | **大小**   | 返回集合A中**元素个数**                                      |
| A.isEmpty()        | 判空       | 判断集合A中元素**个数是否为0**                               |
| A.contains(O o)    | **包含**   | 判断集合A中**是否包含**元素o（**判断内容，调用的是o.equals方法**） |
| A.containsAll(C c) | 包含集合   | 判断集合A中是**否包含集合**c的**全部元素**（**不要求顺序**） |
| A.remove(O o)      | **移除**   | 移除集合A中的元素o，成功返回true（会先调用`contains`判断是否包含） |
| A.removeAll(C c)   | 移除集合   | **移除**集合A中**所有集合c中包含的元素**（移出交集）         |
| A.retain(C c)      | **交集**   | 将集合A与集合c的**交集赋给集合A**                            |
| A.toArray()        | 转换数组   | **生成**一个集合A包含元素组成的**数组**（相当于`Arrays.asList()`逆方法） |
| A.iterator()       | **迭代器** | 生成集合A的一个`Iterator`对象用于迭代遍历                    |

因大多数方法将调用`equals()`方法，因此最好将添加入集合中的自定义类重写此方法



### 1.1 集合数组相互转换

+ 调用集合方法`A.toArray()`可以将集合转换为**包含其全部元素**的数组
+ 调用数组方法`Arrays.asList(a)`可以将数组转换为包含其全部元素的`List`集合

### 1.2 迭代遍历Iterator

`Iterator`即迭代器，用于在不涉及实际对象细节的情况下去遍历操作该对象包含的属性和元素

**迭代器并不保存数据，实际上操作的还是元对象**，类似于一个**指针**，从原始对象的**头部之上**直到尾部

| 方法        | 含义       | 描述                                                         |
| ----------- | ---------- | ------------------------------------------------------------ |
| I.hasNext() | 是否还有   | **判断**该迭代器**是否已经到迭代完成**（是否还有下一个元素） |
| I.next()    | 获取下一个 | 按顺序**获取下一个位置的对象**                               |
| I.remove()  | 移除       | **移除原始集合中当前位置**的元素                             |

### 1.3 foreach

使用`for(O o:c)`的增强`for`循环可以自动调用`iterator`迭代器去迭代**集合或数组**中的对象

```java
      for(Object o:collection){
            System.out.println(o);
        }
```

注意若是`String`和`LocalDate`以及基本数据类型，因其**不可变性**`foreach`指向的**并不是原有**数组或集合中的**对象**



## 2. List

`List`用来存储**有序**、**可重复**的**单组数据集**的**接口**，有以下**实现类**：

| 实现类     | 名称     | 描述                                                     |
| ---------- | -------- | -------------------------------------------------------- |
| ArrayList  | 数组集合 | 线程不安全，高效率，底层是Object[]，使用**数组顺序存储** |
| LinkedList | 链表集合 | 底层是**双向链表**（当**中间插入删除频繁时效率更高**）   |
| Vector     | 矢量集合 | 线程安全，但效率低，底层是Object[]，使用**数组顺序存储** |

+ 链表存储：使用指针指向前后的元素，当**插入或删除时只需要改变指针指向**即可，但**查询效率低下**
+ 顺序存储：使用相连的整块内存，查询时可以按顺序遍历，但**插入和删除时需要改变之后所有元素的位置**

### 2.1 ArrayList

`ArrayList`是`List`接口最常用的构造器，其底层使用**数组**的方式进行实现

#### 2.1.1 构造器

当调用其空参构造器`new ArrayList()`时，会将其中的数组**默认初始化长度为10**，带参构造器会将数组长度**默认为参数长度**。

在`JDK8`中，其空参构造器不再初始化数组，当**第一次调用添加**方法时**才将数组初始化长度10**。

#### 2.1.2 添加

当调用`add()`方法进行添加时，会实现调用内部`ensureCapacityInternal(size+1)`来**判断数组容量是否充足**。

#### 2.1.3 动态扩容

当容量不够时会调用内部方法`grow()`，有三种扩容方式：（若原始长度为size，添加后需要长度为need）

+ 当第一次扩容时会将数组容量**增加为原先的1.5倍**（1.5*size)
+ 若扩容一次后依然不足以添加会**直接将长度扩容为所需要的长度**（need）
+ 当需求长度接近某个阈值时，会直接设置为`int`类型最大值`Integer.MAX_VALUE`



### 2.2 LinkedList

`LinkedList`是`List`使用**双向链表**的一个实现类，当进行**中间插入和删除时效率更高**，但**随机存取效率低下**，其内部使用内部类`Node`来存储存入的数据，并记录头尾地址。



### 2.3 Vector

`Vector`是`List`接口未出现时就存在的`Collection`实现类，后续被归入`List`接口实现类，其具体实现除扩容外（**其扩容方式默认是扩容为原来的2倍**）同`ArrayList`相似，但其所有方法均是由`sychronized`声明的**同步方法**，因此具有**线程安全性**，但执行效率低下。



### 2.4 常用方法

因其是顺序的`Collection`实现类，因此其中添加了部分方法

| 方法                   | 含义       | 描述                                                         |
| ---------------------- | ---------- | ------------------------------------------------------------ |
| A.add(int i,O o)       | 插入       | 在集合A的i位置**插入**元素o（之后元素依次后移）              |
| A.addAll(int i,C c)    | 插入全部   | 在集合A的i位置**插入集合c的全部元素**                        |
| A.get(int i)           | 获取       | **获取**集合A**指定位置**i的存储的元素                       |
| A.indexOf(O o)         | 首位置索引 | 获取元素o在集合A中的**首次**出现**位置索引**（不存在返回-1） |
| A.lastIndexOf(O o)     | 末位置索引 | 获取元素o在集合A中**最后一次**出现的**位置索引**（不存在返回-1） |
| A.remove(int i)        | 移除       | 移除集合A中指定位置i的元素**并将其返回**（`collection`的方法重载） |
| A.set(int i,O o)       | 设置       | 将集合A中指定位置i的元素**修改**为o                          |
| A.subList(int s,int e) | 截取子集   | **生成**一个集合A的**[s,e)的子集**                           |

+ 若`List`存储的整数（包装类），调用`remove(int i)`方法时**默认是索引移除**，**若要调用`Collection`的方法需要显示传入包装类**



## 3. Set

`Set`是`Collection`的一种**无序**，**不可重复**的**单列集合**接口，具有以下实现类：

| 实现类        | 含义            | 描述                                                         |
| ------------- | --------------- | ------------------------------------------------------------ |
| HashSet       | 哈希SET         | 采用HashMap的方式存储，**线程不安全**，**可以存储null**      |
| LinkedHashSet | Link**哈希SET** | HashSet的**子类**，可以按照**添加顺序遍历**，频繁**遍历效率更高** |
| TreeSet       | 树Set           | 采用**红黑树**方式存储，可以按照指定属性**排序**（必须是同一类型） |

+ 无序是指在内存中存储的顺序**不是顺序索引**而是**按照哈希值索引**
+ 不可重复指的按照`equals`判断不能返回`true`，**相同的元素只能存在一个**（采用Hash判断）



### 3.1 HashSet

#### 3.1.1 构造器

`HashSet`无参构造器，默认会构造一个**长度为16的数组**（底层采用的是**HashMap存储**，作为其Key，value均为同一静态Object对象）

#### 3.1.2 添加

当调用`add()`方法时，若是第一次添加先**计算其Hash值**、并通过内部**散列函数**获取索引位置，并**存储在数组的索引位置**。

当非第一次添加时，调用元素`HashCode()`计算Hash值和索引位置，若索引位置**已经存在**元素（或链表），**对Hash值进行比较**：

+ 若不等，这以**链表**的方式在**该位置存储**元素，并**连接在**之前存在元素的**后方**（JDK在前方）
+ 若相同，则比较`equals`，若返回**`true`则添加失败**，否则**`false`使用链表连接在后**



### 3.2 LinkedHashSet

`LinkedHashSet`是`HashSet`的子类，其所有结构与方法与`HashSet`相同，仅仅在**每个元素之间多加了一个指针按照添加顺序进行指向**，相当于在`HashSet`同位置单链表的基础上修改为**双链表**，当**频繁遍历时效率更高**。



### 3.3 TreeSet

`TreeSet`其存储方式使用**红黑树**。存储的数据必须是相同类的对象（**包括子类，实现类**），且**实现`Comparable`接口或者自定义`Comparator`**，其会自动将其添加的数据进行比较排序，迭代时将会**按照排列顺序输出**。

其判断元素是否相同采用的是比较大小，**若相同（返回0）则添加失败**（红黑树二叉树性质决定）

#### 3.3.1 构造器

`TreeSet`无参构造器构造后，将使用元素的**`ComparaTo()`方法进行比较**（必须实现`Comparable`方法，否则抛出异常）

`TreeSet`单参构造器，需要**传入参数`Comparator`自定义比较器**，添加元素将使用自定义比较器比较。



# 三、Map接口

`Map`用来存储键值对模式具有映射关系的数据，一般称为**键值对映射表**或**字典集**，主要有以下三个实现类：

| 类/接口  | 含义           | 描述                                                         |
| -------- | -------------- | ------------------------------------------------------------ |
| HashMap  | **哈希映射表** | 线程不安全，高效率，**key-value均可为null**，底层为**数组+链表+红黑树** |
| TreeMap  | **树映射表**   | 可以保证存储数据自动**按照`key`进行排序**，底层为**红黑树**  |
| Hashtabl | 哈希表         | 1.0存在的实现类，线程安全，单效率低，不能存储null            |

+ `HashMap`具有子类`LinkedHashMap`，其可以在遍历时按照添加顺序（实现方式同LinkedHashSet，采用双链表），**当频繁遍历时效率更高**
+ `Hashtabl`具有子类`Properties`主要用来处理配置文件，其**key-value均为`String`**

## 1. 通用方法

| 方法               | 含义          | 描述                                                  |
| ------------------ | ------------- | ----------------------------------------------------- |
| A.put(k,v)         | **添加/修改** | 在字典集A中**添加/修改**key=value的数据               |
| A.putAll(M m)      | 添加全部      | 将字典集m中的全部数据**添加到**字典集A中              |
| A.remove(k)        | **移除**      | 将字典集中key=k的数据移除，并返回其value              |
| A.clear()          | 清除          | 将字典集A中的全部数据清除（非A=null）                 |
| A.get(k)           | **获取**      | 获取字典集A中key=k的value                             |
| A.containsKey(k)   | 判断包含Key   | 判断字典集A中是否存在key=k                            |
| A.containsValue(v) | 判断包含Value | 判断字典集A中是否存在value=v                          |
| A.size()           | **获取大小**  | 返回字典集A中所有数据的个数                           |
| A.isEmpty()        | 判空内容      | 判断字典集A中是否存在数据                             |
| A.keySet()         | **遍历Key**   | **生成**一个由字典集A中**所有key组成的Set集合**       |
| A.values()         | **遍历Value** | **生成**一个由字典集A中**所有Value组成的list集合**    |
| A.entrySet()       | **遍历Entry** | **生成**一个由字典集A中**所有key-value组成的Set集合** |

其中**`Entry`是`Map`的内部接口**，包括以下两个方法

| 方法         | 含义      | 描述                 |
| ------------ | --------- | -------------------- |
| A.getKey()   | 获取key   | 获取元素A存储的key   |
| A.getValue() | 获取value | 获取元素A存储的value |
| A.setValue() | 设置value | 设置元素A存储的value |



## 2. HashMap

`HashMap`存储key-value存储时，**`key`不可重复且有序，`value`可重复无序**，其底层实际**存储的是单个`Entry`元素**，key-value均作为`Entry`的属性。其中`Entry`是**无序存储的**。

**作为`key`的类必须重写`hashCode()`和`equals()`方法**

### 2.1 实现方式

+ `HashMap`实例化后，会默认创建一个**长度为16的`Entry[]`**

+ 调用`put(k,v)`方法后，会**调用`key.hashCode()`计算key的hash值**，并使其**通过散列算法**得到在`Entry[]`数组中**存放位置**。
  + 若该位置数据为空，则直接添加在此位置上
  + 若该位置数据不为空，则与**比较该位置上数据的hash值**，若均不同则以链表的方式存放在之后，否则**调用当前对象的`equals()`方法**与已经存在的数据比较
    + 若返回`true`：**用value替换该位置的value**
    + 若返回`false`：按链表存储在之后
+ 默认的扩容方式为扩大为**原有的两倍**，并复制原有数据

其中JDK8对实现方式进行了修改：

1. 底层**使用`Node[]`**替代`Entry[]`存储，其是`Entry`的子类
2. **实例化不会创建数组**，当**第一次调**用`put(k,v)`方法时才**创建**长度16的数组
3. 当**某一个位置上链表长度>8**且数组长度大于64时，**此索引位置上**的所有数据由链表**变为红黑树存储**



### 2.2 构造器

当调用构造器时候，会默认调用内部构造器，并给其赋值默认数组长度（默认是16，若使用有参构造，会是大于等于自定义值的最近的一个**2的倍数**）和**负载因子**（默认0.75），通过`数组长度*负载因子`会得到**临界值**（默认12，当**存储元素数量大于此且当前位置不为null时会触发扩容**）

### 2.3 添加

调用`put(k,v)`方法时，会首先判断Key是否为null，若是null则调用`putForNullKey(v)`将其value添加。

否则，计算key的hash值，并调用`forIndex(key,size)`方法**将hash和数组长度做`&`运算**，得出存储位置`index`。

若index位置的元素为null则直接添加，否则进行判断是否添加或替换，若替换则返回之前的对象。否则执行添加：

+ **若当前数量大于临界值且当前位置不空，者会调用扩容**，将数组长度扩容为之前两倍，并计算全部元素的位置依次加入新数组中。
+ 否则会直接在尾部以链表（Node）的方式进行插入，当当前位置的**Node长度超过8**并且数**组总长**度不超过**64时**会对数组进行扩容，**否则会将当前位置改为红黑树存储`TreeNode`**

### 2.3 LinkedHashMap

`LinkedHashMap`是`HashMap`的**子类**，其重写了父类的`newNode()`方法，并构建了`Node`的子类`Entry`，在其中存储`before`和`after`的对象，在各个元素间形成双链表记录添加时的先后顺序。



## 3. TreeMap

`TreeMap`存储键值对，底层实现同TreeSet相似，**要求Key都各都为同一类对象**，且实现`Comparable`接口或自定义`Comparator`接口，底层使用`红黑树`，**会自动按照Key的大小进行排序**。



## 4. Hashtable

`Hashtable`是一个古老的字典集对象，其早于`Map`接口，所有方法均为同步方法，因此效率低下，后在JDK1.2中被归为`Map`的实现类，使用极少，主要使用其子类`Properties`处理配置文件中的属性



### 4.1 Properties

`Properties`主要用来处理配置文件，其**key-value均为`String`**，其可以加载文件流来读取文件中的属性值，主要方法如下

| 方法               | 含义           | 描述                                          |
| ------------------ | -------------- | --------------------------------------------- |
| A.load(r)          | 加载           | 加载文件流对象，通过其初始化属性A             |
| A.loadFromXML(i)   | 加载XML        | 加载XML文件对象，通过其初始化属性A            |
| A.getProperty(K k) | 读取属性       | 读取key为k的属性的值，不存在返回null          |
| A.getProperty(k,d) | **读取并默认** | 读取key为k的属性的值，**不存在则使用默认值d** |



### 4.2 读取属性文件

使用`Properties`可以加载属性文件并读取，案例如下（属性文件在src同级目录）

```java
 public void sample() throws Exception {
        // 读取文件流
        FileInputStream fileInputStream = new FileInputStream("map.properties");
        Properties properties = new Properties();
        
        // 加载流对象
        properties.load(fileInputStream);
        
        // 获取
        String name = properties.getProperty("name");
        System.out.println(name); // lpc
        // 获取不存在
        System.out.println(properties.getProperty("name2")); // null
        // 获取并设置默认值
        System.out.println(properties.getProperty("name2","lengpucheng")); // lengpucheng
        System.out.println(properties.getProperty("name","lengpucheng")); // lpc
        
        // 关闭文件流
        fileInputStream.close();
    }
```



# 四、集合工具类Collections

`Collecations`是**操作`Collecation`和`Map`接口**的工具类，其中封装了与之相关的**静态方法**：

| 方法                         | 含义         | 描述                                                         |
| ---------------------------- | ------------ | ------------------------------------------------------------ |
| reverse(List list)           | **反转**     | 将List集合**反转**                                           |
| shuffe(List list)            | **随机排序** | 将List集合**随机排序**                                       |
| sort(List list)              | 自然排序     | 将List集合按照`Comparable`接口排序（**元素需实现此接口**）   |
| sort(List list,Comparator c) | 自定义排序   | 将List集合按照自定义`Comparator`接口排序                     |
| swap(l,int a,int b)          | **交换**     | 将List集合i中指定位置a和b的元素交换位置                      |
| max/min(collection)          | **自然极值** | 返回Collection集合中的按自然排序最大/小值<br>（元素需要实现`Comparable`接口） |
| max/min(c,comparator)        | 自定义极值   | 返回Collection集合中按自定义`Comparator`接口的最大/小值      |
| frequency(collection,obj)    | **频数**     | 返回Collection集合中指定元素obj出现的频数                    |
| copy(dest,src)               | **复制**     | 将Collection集合src中的全部元素复制到dest中<br>（dest的size必须和src一致，可以用null Object填充） |
| replaceAll(List,old,new)     | **修改**     | 将List集合中**所有的元素old替换为元素new**                   |
| synchronizedXxx(C)           | **同步方法** | **将集合C变成一个线程安全的同步集合X**                       |

**`synchronizedXXX()`方法实际上在内部重写了`collection`方法并加上了`synchronized`关键字，其支持`collection`和`Map`中的所有实现类。**

## *. 正确复制

`copy`方法需要**集合大小一致才可复制**（当dest<src时抛出异常，dest>src时会保留dest多余的元素），因此可以直接**使用空对象填充dest**，**且复制后的dest不支持`remove()`方法**

```java
List dest=Arrays.asList(new Object[src.size()]);
        Collections.copy(dest,src);
```



# 五、泛型Generic

`泛型`类似于一个标签，给集合或方法贴上标签后，其之后只能放入或取出**指定类型**的类对象。即在定义类、接口时**标识某个类的属性或方法的类型或返回值为某一个指定类型**，泛型是一个类型，**不能为基本数据类型**。

## 1.泛型结构

### 1.1 泛型类/接口

可以用符号在类中声明某一个泛型，并将其**作为一个类**在类中使用，**其具体类型在声明和实例化时给出**，若**不声明将默认为`Object`**，**当多个泛型时候用`,`分隔**

**异常类不能声明为泛型**

```java
static class Person<L>{
        String name;
        int age;
        L language;

        public String getName() {
            return name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public L getLanguage() {
            return language;
        }

        public void setLanguage(L language) {
            this.language = language;
        }
    }
```

JKD7中新添加钻石语法糖，可以在声明中使用泛型后，在实例化时直接使用`<>`

#### 1.1.1 接口/类继承

当接口或类进行继承/实现时，可以将带泛型的父类进行指明，则**不再是一个泛型，变为指定类型**

```java
static class ChinesePerson extends Person<Chinese>{
        
    }
```

**否则依然是一个泛型结构**,但不一定全部保留

```java
static class ChineseStudent<T> extends Person<Chinese,T>{
    // 继承指定了person的其中一个
}
```

#### 1.1.2 泛型数组

泛型无法实例化，但可以通过强制的方式，**将Object转换为泛型**

```java
T[] t=(T[])new Ojbect[10];
```



### 1.2. 泛型方法

**泛型方法是独立于类的泛型**，方法中出现泛型结构即可，与类是否为泛型或泛型类型无关。**为避免泛型标签与类名冲突，需要在方法返回值前加上`<>`并在其中指定泛型标签**。

```java
public static <E> List<E> getList(E[] es) {
        return Arrays.asList(es);
    }

public static <E> E get(){
        return (E)new Object();
    }
```

泛型方法的泛型类型将**根据参数或接收的类型来指定泛型类型**



## 2. 泛型多态与继承

若A是B的父类，`G<T>`为泛型，则

+ `G<A>`与`G<B>`不具有子父关系，为并列关系。

+ `A<G>`与`B<G>`依然**保持子父类关系**

```java
List<String> list=new ArrayList<>()
```

### 2.1 通配符

`?`可以作为通配符，作为并列泛型的公共父类

```java
List<String> str=new ArrayList<>();
List<Integer> inter=new ArrayList<>();
List<?> list;
list=str;
list=inter;
```

但当使用`?`通配时，泛型类中发方法泛型**参数将只能填入`null`**,泛型**返回值将会向上转型为`Object`**

### 2.2 条件泛型

通配符`？`或者是泛型泛型标识可以使用条件来限定泛型类型：

+ `<T extend A>`: 参数**必须为A或A的子类**，返回值为A
+ `<T super A>`：参数**必须为A或A的父类**，返回值为`Object`