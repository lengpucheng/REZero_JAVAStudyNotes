# 一、File

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



## 3. 使用

参考[文件树案例](../src/cn/hll520/java/iosample/FileTree.java)



# 二、IO与流

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



# 三、节点流

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





# 四、缓冲流