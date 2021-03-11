# 一、文件File

`File`的一个对象表示文件目录中的一个**文件或文件目录**。

## 1. 构造器

| 构造器                        | 参数             | 含义                     |
| ----------------------------- | ---------------- | ------------------------ |
| String pathname               | 文件路径/文件名  | 直接写是相对路径         |
| String parent<br>String child | 父路径<br>子路径 | 一般用于文件目录和文件名 |
| File parent<br>String chile   | 父路径<br>子路径 | 使用文件对象作为父路径   |

+ **非绝对路径或`./`开头时，默认父路径为`工程目录`**，若**以`/`开头则路径为`C:\`**或`root`
+ 非绝对路径在单元测试下相对参考路径为`src/..`（模块）
+ 不同系统和不同路径间分隔符不同，可以使用常量`File.separator`替代。
+ 其对象在**不进行读写时仅为内存对象，与实际文件无关**，即File对象**不一定必须有物理文件与之对应**。



## 2. 常用方法

### 2.1 获取属性

| 方法                | 含义             | 用法                                                         |
| ------------------- | ---------------- | ------------------------------------------------------------ |
| F.getAbsolutePath() | 绝对路径         | 获取文件F的绝对路径                                          |
| F.getPath()         | 相对路径         | 获取文件F的相对路径<br>（**当使用绝对路径构造时为绝对路径**） |
| F.getName()         | **文件名**       | 获取文件F的名称                                              |
| F.getParent()       | 父路径           | 获取文件F的父路径<br/>（**当使用绝对路径构造时为绝对路径**） |
| F.length()          | **文件大小**     | 获取文件F的大小（**单位B/字节**）                            |
| F.lastModified()    | **最后修改时间** | 获取文件F的最后修改时间（**时间戳**）                        |
| D.list()            | 目录下**文件名** | 获取文件目录D下全部文件的**名称**，**返回数组**<br>（若D**不为目录时返回null**，**不存在时抛异常**，下同） |
| D.listFiles()       | 目录下**文件**   | 获取文件目录D下**全部文件对象**，返回**数组**                |

### 2.2 执行判断

| 方法            | 含义             | 用法                              |
| --------------- | ---------------- | --------------------------------- |
| F.exists()      | **是否存在**     | 判断文件对象F是否**物理存在**     |
| F.isHidden()    | 是否隐藏         | 判断文件对象F是否是隐藏文件/目录  |
| F.isFile()      | **是否为文件**   | 判断文件对象F是否是**单个文件**   |
| F.isDirectory() | **是否为文件夹** | 判断文件对象F是否是**文件目录**   |
| F.canRead()     | 是否可读         | 判断文件对象F是否拥有**可读权限** |
| F.canWrite()    | 是否**可写**     | 判断文件对象F是否拥有**可写权限** |
| F.canExecute()  | **是否可执行**   | 判断文件对象F**是否是可执行文件** |

### 2.3 文件操作

| 方法               | 含义             | 用法                                                         |
| ------------------ | ---------------- | ------------------------------------------------------------ |
| F..createNewFile() | **创建文件**     | 若文件对象F**物理不存在则创建物理文件**<br>若文件**路径中父目录不存在时将抛出异常**<br>（返回是否操作成功，下同） |
| F.mkdir()          | **创建目录**     | 若文件对象F非物理目录，则创建该目录<br>若路径中有**父目录不存在时创建失败** |
| F.mkdirs()         | **完整创建目录** | 同上，若**父目录不存在会一并创建**                           |
| F.delete()         | **删除文件**     | 删除文件对象F对应的文件/目录（**直接删除**）<br>删除目录时目录中不能含义对象 |
| F.reNameTo(N)      | **剪切重命名**   | 将文件F**移动并重命名为文件对象N对于的名称和路径**<br>(F为真实文件，**N对象对应物理文件不能存在**)，返回修改成否 |

**2.1中各个属性均对应有`set`方法可以设置只读、只写、拥有、授权等操作**



## 3. 使用/文件树

参考[文件树案例](../src/cn/hll520/java/iosample/FileTree.java)



# 二、IO流

`I/O`即input/output，即输入和输出用于处理设备之间的**数据传输**。包括读写和网络均使用I/O，在JAVA中数据的输入和输出使用**流（stream）的形式进行**。**即I/O流相当于一条连接数据源和使用者的一条管道**，**数据在这条管道中单向流动**。

## 1. 基本抽象流

+ 按照**流向方向**可以分为**输入流**和**输出流**
+ 根据**数据单位**类型分为：**字节流**（8bit）和**字符流**（16bit，**一个字符两个字节**）
+ 按流的**操作**可以分为：**节点流**（实际链接IO两端的管道流）和**处理流**（**对节点流进行包装增强操作的流**）

在JAVA中**所有流类型均有以下4中抽象类派生而来**：

| 抽象流   | 字节         | 字符   |
| -------- | ------------ | ------ |
| **输入** | InputStream  | Reader |
| **输出** | OutputStream | Writer |

且其派生类基本上以以上名称结尾

![image-20210310165046137](C:\ProjectSpace\从零开始的JAVA学习笔记\JAVA01-进阶\JAVA01-进阶笔记\images\image-20210310165046137.png)



## 2. 基本常用流

File**文件流是**除基本抽象流之外的**基本节点流**，**其余均为处理流**，常用流如下：

| 分类           | 字节输入流                | 字节输出流                 | 字符输入流              | 字符输出流               |
| -------------- | ------------------------- | -------------------------- | ----------------------- | ------------------------ |
| **基本流**     | **InputStream**           | **OutputStream**           | **Reader**              | **Writer**               |
| **节点流**File | ***FileInputStream***     | ***FileOutputStream***     | ***FileReader***        | ***FileWriter***         |
| 数组Array      | ByteArrayInputStream      | ByteArrayOutputSteam       | CharArrayReader         | CharArrayWriter          |
| 管道Piped      | PipedInputStream          | PipedOutputStream          | PipedReader             | PipedWriter              |
| 字符串String   |                           |                            | StringReader            | StringWriter             |
| 缓冲Buffered   | ***BufferedInputStream*** | ***BufferedOutputStream*** | ***BufferedReader***    | ***BufferedWriter***     |
| **转换流**     |                           |                            | ***InputStreamReader*** | ***OutputStreamWriter*** |
| 对象流         | ***ObjectInputStream***   | ***ObjectOutputStream***   |                         |                          |
|                | FilterInputStream         | FilterOutputStream         | FilterReader            | FilterWriter             |
| 打印流Print    |                           | PrintStream                |                         | PrintWriter              |
| 推入Pushback   | PushbackInputStream       |                            | PushbackReader          |                          |
| 特殊数据Data   | DataInputSteam            | DataOutputStream           |                         |                          |



## 3. 流的操作

在JAVA中数据的IO都是一套标注话的流程，由以下**标注四步构成**：

1. **数据源/目标**实例化（**资源实例化**）
2. 使用数据源**实例化数据流**
3. 流的**操作**（`read()`、`writer`）（**资源的操作**）
4. 调用`close()`关闭流（**资源的回收**）



# 三、IO节点流

基本的节点流为`FileInputStream`/`FileOutputStream`/`FileReader`/`FileWriter`等，基本操作依然和其他流一样

## 1. 字符流FileReader/Writer

字符流用于处理字符\文本数据，**若处理其他非文本数据处理后的目标将异常**

### 1.1 输入流FileReader

直接使用`文件File`进行字符输入流对象进行实例化，可以调用如下方法进行文件读取（输入到内存）：

| 方法         | 参数                           | 返回值                                   | 含义                                                         |
| ------------ | ------------------------------ | ---------------------------------------- | ------------------------------------------------------------ |
| A.read()     |                                | `int`:读取的个数<br>到**文件末尾返回-1** | 读取文件A，**每次一个字符**                                  |
| A.read()     | char[]                         | 同上，读取完毕**下一次为-1**             | 每次读取`char[]`长度<br>为覆盖读取，每次**不会清空数组**为**按顺序覆盖** |
| ~~A.read()~~ | ~~char[]<br>offset<br>length~~ | ~~同上~~                                 | ~~从offset位置开始，每次读取length长度到char[]中~~           |

### 1.2 输出流FileWriter

使用`文件File`或`文件路径`进行实例化，同时可以使用第二个参数`append`赋值代表是否追加写入

+ 若文件/路径对应物理**文件不存在会自动创建**
+ 若文件存在且`append`为**false**将**直接覆盖文件**
+ 若文件存在且`append`为**true将在文件末尾追加写入**

可以使用如下方法将内存中的数据写入文件：

| 方法          | 参数                            | 含义                                                         |
| ------------- | ------------------------------- | ------------------------------------------------------------ |
| A.write()     | String                          | 向文件输出流中写入一个字符串                                 |
| A.write()     | char[]                          | 同上                                                         |
| ~~A.write()~~ | ~~char[]/String<br>off<br>len~~ | ~~向文件输出流中从字符串/char[]的**off位置开始写入长度为len的字符**~~ |

### 1.3 文本文件复制

```java
 public void copyFile() {
        // 1. 数据源/目标实例化
        File file = new File(path, "hello2.txt");
        File newFile = new File(path, "helloCopy.txt");

        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            // 2. 实例化 数据流
            fileReader = new FileReader(file);
            fileWriter = new FileWriter(newFile);

            // 3. 流操作
            char[] buff = new char[16]; // 3.1 每次读取大小
            int len;
            // 3.2 获取读取长度 并判断是否到尾部 -1
            while ((len = fileReader.read(buff)) != -1) {
                // 3.3 写入读取的长度
                fileWriter.write(buff, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭流
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
```

## 2. 字节流FileInputStream/FileOutputStream

字节流可以用于处理任意二进制数据(**包括文本字符**)

但**在处理文本字符时**可能导致**读取的字节恰为某个字符的一部分**，导致字符出现乱码现象。

### 2.1 输入流FileInputStream

直接使用`文件File`进行字节输入流对象进行实例化，可以调用如下方法进行文件读取（输入到内存）：

| 方法         | 参数                           | 返回值                                   | 含义                                                         |
| ------------ | ------------------------------ | ---------------------------------------- | ------------------------------------------------------------ |
| A.read()     |                                | `int`:读取的个数<br>到**文件末尾返回-1** | 读取文件A，**每次一个字节**                                  |
| A.read()     | byte[]                         | 同上，读取完毕**下一次为-1**             | 每次读取`byte[]`长度<br>为覆盖读取，每次**不会清空数组**为**按顺序覆盖** |
| ~~A.read()~~ | ~~char[]<br>offset<br>length~~ | ~~同上~~                                 | ~~从offset位置开始，每次读取length长度到byte[]中~~           |

### 2.2 输出流FileOutputStream

使用`文件File`或`文件路径`进行实例化，同时可以使用第二个参数`append`赋值代表是否追加写入

+ 若文件/路径对应物理**文件不存在会自动创建**
+ 若文件存在且`append`为**false**将**直接覆盖文件**
+ 若文件存在且`append`为**true将在文件末尾追加写入**

可以使用如下方法将内存中的数据写入文件：

| 方法          | 参数                     | 含义                                                         |
| ------------- | ------------------------ | ------------------------------------------------------------ |
| A.write()     | byte                     | 向文件输出流中写入一个byte(二进制数 -128~127)                |
| A.write()     | byte[]                   | 同文件输出流中写入一个byte[]数组                             |
| ~~A.write()~~ | ~~byte[]<br>off<br>len~~ | ~~向文件输出流中从byte[]的**off位置开始写入长度为len的字符**~~ |

### 2.3 二进制文件复制

也可以复制文本文件，写入和复制都是在进行完毕后因此**字符被中断后又会重新组装**。

```java
public void copyImage(){
        // 1. 实例化数据源/目标
        File file=new File(path,"lpc.jpg");
        File newFile=new File(path,"lpcCopy.png");
        FileInputStream inputStream = null;
        FileOutputStream outputStream= null;

        try {
            // 2. 流的实例化
            inputStream = new FileInputStream(file);
            outputStream = new FileOutputStream(newFile);
            // 3. 操作流
            byte[] buff=new byte[16];
            int len;
            while((len=inputStream.read(buff))!=-1)
                outputStream.write(buff,0,len);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭流
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
```



# 四、IO主要处理流

## 1. IO缓冲流/Buffered

`Buffered`缓冲流，其**套接**在**其他流**之上（可以是节点流**也可以是其他处理流**），利用内存缓存实现对节点**流传输速度的提升**。其主要原理为在内存中使用一块缓存区当缓存区填满后一次性写入或输出，其**默认缓冲区大小为8192**。

### 1.1. 缓冲流操作

其具体使用为以下标注五部：

1. 实例化数据源/目标对象（资源实例化）
2. 使用资源实例化节点流
3. **使用节点流实例化处理流**
4. 使用处理流进行操作
5. **关闭处理流**（关闭处理流时**节点流会自动关闭**）

2和3可以**使用匿名对象方式进行合并**



### 1.2. 新增方法

作为处理流，其**套接类型必须一致**，字节流只能使用字节缓存流套接，字符只能是字符缓存流套接，其常用方法和形式与节点流非常相似，均参照IO基本抽象流，但又定义了如下方法：

| 方法         | 含义       | 属于                                                     | 用法                                                         |
| ------------ | ---------- | -------------------------------------------------------- | ------------------------------------------------------------ |
| O.flush      | 刷新缓冲区 | **输出缓冲流**<br>BufferedOutputStream<br>BufferedWriter | 将缓冲区中数据**一次性写入资源并清空缓冲区**<br>默认是当**缓冲区填满后自动调用** |
| I.readLine() | 读取一行   | **字符输入缓冲流**<br>BufferedReader                     | 每次**读取一行数据**，**文件末尾返回null**<br>**返回的值不包含换行符**，需要手动添加 |
| O.newLine()  | 下一行     | **字符输出缓冲流**<br>BuffereWriter                      | **换行**，**下一次**写入将在新的一行                         |



### 1.3. 字符复制（使用Line）

```java
 public void copyStr(File file, File newFile){
        BufferedReader reader= null;
        BufferedWriter writer= null;
        try {
            // 2. 实例化话流
            reader = new BufferedReader(new FileReader(file));
            writer = new BufferedWriter(new FileWriter(newFile));

            // 3. 操作
            String line;
            while((line=reader.readLine())!=null){
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4.关闭流
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
```



## 2. 转换流

`InputStreamReader`和`OutputStreamWriter`作为转换流，可以将字节和字符进行转换，其中：

+ `InputStreamReader`：将**字节输入流**转换为**字符流输入**，对应**字符解码**
+ `OutputStreamWriter`：将**字符输出流**转换为**字节流输出**，对应**字符编码**

在构造器中**第一个参数为对应的流**，**第二个参数为字符集**（若不使用为本地字符集）

### 2.1. 字符集

字符集是二进制数据对文本字符映射的一种编码集，常用的字符集有：

+ `ASCII` :美国标准信息交换码，用一个字节的**7位表示字符**
+ `ISO8859-1`：拉丁、欧洲标准，用一个字节**8位表示字符**
+ `GB2312`：中国国标编码，**最多两个字节**表示字符（兼容ASCII）
+ `GBK/BIG5`：国标扩充/大写汉字，**最多两个字节**表示字符（兼容ASCII）
+ `Unicode`：国际标准码字符集，包含人类全部字符，所有字符**都用2个字节**表示
+ `UTF-8`：国际编码规则（Unicode的一种规则），用**1-4个字节**表示字符（兼容ASCII）

当字符集为可变长字节编码时，为区别到底是几个字节存储，有如下规则：

+ 在GB/BIG中**第一个字节的第一位为判断位**（0表示一个字节等）
+ 在UTF-8中**第一个字节的前几位**为`0`表示一个字节，`110`表示两个，`1110`表示三个、`11110`表示四个字节，之后每个字节用`10`开头，之后使用`Unicode`中的数字进行对应进行填充。

### 2.2. 字符集转换

```java
 public void tranType(File file,String type,File newFile,String newType)throws IOException{
        InputStreamReader reader=new InputStreamReader(new FileInputStream(file),type);
        OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream(newFile),newType);

        char[] chars=new char[1024];
        int len;
        while ((len=reader.read(chars))!=-1)
            writer.write(chars,0,len);


        writer.close();
        reader.close();
    }
```







## 3. 使用

### 3.1 文件加密

请参考[文件加密](../src/cn/hll520/java/iosample/nodefile/FileSecret.java)

### 3.2 词频分析

请参考[词频分析](../src/cn/hll520/java/iosample/nodefile/WorldAnalysis.java)





# 五、IO其他处理流

## 1. 标准输入输出流

`System`类中，定义了与控制台输入输出相关的流：

+ `System.in`为标准输入流，默认从键盘输出（实际上为一个`InputStream`输入流）
+ `System.out`为标准输出流，默认从控制台输出（实际上为一个`PrintStream`打印流）

**可以通过`System`中的`set`方法去修改其中的流**

| 方法                           | 用法                             |
| ------------------------------ | -------------------------------- |
| System.setIn(InputSteam in)    | **设置**标准输入流中的**输入流** |
| System.setOut(PrintStream out) | **设置**标准输出流中的**打印流** |

### 1.1 从键盘读取输入

在不使用`Scanner`的情况下可以通过**转换流**来**套接**`System.in`读取键盘输入的内容

```java
public static void in() {
        BufferedReader bufferedReader = null;
        try {
            InputStreamReader reader = new InputStreamReader(System.in);
            bufferedReader = new BufferedReader(reader);
            while (true) {
                // 读取一行
                String s = bufferedReader.readLine();
                if ("e".equalsIgnoreCase(s) || "exit".equalsIgnoreCase(s))
                    return;
                System.out.println(s.toUpperCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
```



## 2.打印流Print

`PrintStream`和`PrintWriter`打印流可以**将基本数据类型转换为字符串**，并进行输出，其中重载了许多`Print()`和`Println()/换行`方法，用于打印输出，若是对象会**默认调用`toString()`方法**，其中`System.out`为`PrintStream`**字节打印流**，常用方法如下：

| 方法/构造器                  | 参数                                                         | 用法                             |
| ---------------------------- | ------------------------------------------------------------ | -------------------------------- |
| **new PrintStream/Writer()** | OutputSteam/Reader os  输出流<br> boolean autflush  **是否自动刷新缓存** | 使用**输出流来构造一个打印流**   |
| print()                      | 基本数据类型和各对象                                         | 打印输出**对象自动调用toString** |
| println()                    | 基本数据类型和各对象                                         | 同上，**末尾自动添加换行符**     |

### 2.1 自定义输出

```java
  public void diyTest() throws IOException{

        PrintStream printStream = new PrintStream(new FileOutputStream("src/res/print.txt"), true);
        System.setOut(printStream);
        for(int i=0;i<255;i++){
            System.out.println((char)i);
        }
        printStream.close();
    }
```



## 3. 数据流Data

`DataInputStream`和`DataOutputStream`作为数据流，用于将**基本数据类型**和**字符串**写入到文件或从文件中读取，**写入和读取必须顺序一致**，否则会抛出异常，其需要**使用节点流进行实例化**。

写入和读取字符串**使用`wtriteUTF()`和`readUTF()`使用Unicode编码写入**

### 3.1 数据读写

```java
 public void date() throws IOException {
        // 写人
        DataOutputStream outputStream=new DataOutputStream(new FileOutputStream("src/res/data.io"));
        outputStream.writeInt(9866);
        outputStream.flush();// 手动刷新(立即写入)
        outputStream.writeBoolean(true);
        outputStream.flush();// 手动刷新(立即写入)
        outputStream.writeUTF("java");
        outputStream.flush();// 手动刷新(立即写入)
        outputStream.writeDouble(1999.0125);
        outputStream.flush();// 手动刷新(立即写入)
        outputStream.close();
        // 读取
        DataInputStream inputStream=new DataInputStream(new FileInputStream("src/res/data.io"));
        System.out.println(inputStream.readInt());
        System.out.println(inputStream.readBoolean());
        System.out.println(inputStream.readUTF());
        System.out.println(inputStream.readDouble());
        inputStream.close();
    }
```



# 六. 对象流和序列化

`ObjectInputStream`和`ObjectOutputStream`可以将**对象**存或**基本数据类型**储到数据源中或从中读取

## 1. 序列化

将对象转换为二进制的过程称为**序列化**，将二进制数据流转换为对象的过程被称为**反序列化**。序列化和反序列化可以将对象和数据**转换为与平台无关的二进制数据流**，**用于保存或网络传输**。在JAVA中默认为：

+ `序列化`：将基本数据类型或对象使用`ObjectOutputStream`**保存**的过程
+ `反序列化`：从`ObjectInputStream`中**读取**对象或基本数据类型的过程
+ **`static`和`transient`修饰的属性不能序列化和反序列化**

**目前主要将对象转换为`JSON`字符串用于存储和传输**



## 2. Serializable接口

`Serializable`接口是一个**标记接口**，内部没有抽象方法，只要是实现该接口的对象均可以被JAVA默认序列化的反序列化，按照规范需要在对象中添加如下属性：

```java
static final long serialVersionUID = 42L
```

`serialVersionUID`用于标识当前对象的版本，**只有版本一致时，序列化和反序列化才会成功**。**若不显示定义该属性则JAVA会自动生成**，但**当类进行修改后会改变其值**导致反序列化异常！

默认基本数据类型可以序列化，若对象中有属性为对象则该属性对象必须可序列化该对象才可以序列化（**可序列化对象的所有属性必须都可序列化**）

### 2.1 transient关键字

**`transient`关键字声明的属性，在序列化和反序列化时会被忽略**



## 3. 对象流序列化

```java
 public void writer() throws IOException, ClassNotFoundException {
        // 序列化
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("src/res/oos.data"));
        oos.writeObject("this is obj");
        oos.writeObject(new Person("lpc",21,99.5));
        oos.close();
        
        // 反序列化
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream("src/res/oos.data"));
        System.out.println(ois.readObject());
        System.out.println(ois.readObject());
        ois.close();
    }
```



# 七、随机存取RandomAccessFile

`RandomAccessFile`类是IO包下直接继承与`Object`的一个类，其实现了`DataInput`和`DataOutput`接口，**既可以读又可以写**，其写入将**从头开始对原文件进行覆盖**（不会清空写入之后的内容）。

+ **其主要用于断点续传，或者分片下载等**
+ 由于其中间插入缓存使用较差，因此可以**使用数字节组流代替`ByteArray`**



## 1. 构造器

其构造器和普通流相似，第一个参数为`File`文件或者`String`文件路径，第二个参数`mode`为打开文件模式：

+ `r`：只读方式打开
+ `rw`：读写方式打开
+ `rwd`：读写方式打开，并实时更新内容
+ `rws`：读写方式打开，实时更新内容和元数据

若**以`r`方式构造则当文件不存在时不会自动创建**，**其他将自动创建**。



## 2.特殊方法

使用其对象的**`seek(long pos)`方法可以将指针移动到指定位置**

并从此位置开始读取和操作，**配合使用`File`对象中的`length()`方法可以在文件末尾插入**。

### 2.1 指定位置读取

```java
        accessFileR.seek(2);
        accessFileR.read();
        accessFileR.close();
```

### 2.2 末尾插入

```java
 public void peek()throws IOException{
        File file=new File("src/res/lpc2.asc");
        RandomAccessFile accessFileR=new RandomAccessFile(file,"rw");
        accessFileR.seek(file.length());
        accessFileR.writeUTF("\nLPC HELLO!");
        accessFileR.close();
    }
```

### 2.3 中间插入

```java
public void midAdd(File file, long pos, Object... value) throws IOException {
        // 实例化对象
        RandomAccessFile access = new RandomAccessFile(file, "rw");
        // 移动到插入位置
        access.seek(pos);
        // 缓存插入位置后的数据
        StringBuilder builder = new StringBuilder((int) file.length());
        int len;
        byte[] bytes = new byte[1024];
        while ((len = access.read(bytes)) != -1) {
            builder.append(new String(bytes, 0, len));
        }
        // 将指针重新回到插入位置
        access.seek(pos);
        // 写入数据
        if (value != null) {
            for (Object obj : value)
                access.writeBytes(obj.toString());
        }
        // 将缓存的数据还原回插入点后
        access.writeBytes(builder.toString());
        // 关闭流
        access.close();
    }
```