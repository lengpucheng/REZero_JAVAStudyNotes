# 一、计算机网络

每一台连接网络的计算机都有一个**主机号即IP地址**，在每一个主机上可能有若干各应用程序进行网络通信，因此可以使用**端口号`Port`区别不同应用**，同时需要使用固定的协议进行数据编码和传输，主要标准分层如下：

![image-20210312093847241](C:\ProjectSpace\从零开始的JAVA学习笔记\JAVA01-进阶\JAVA01-进阶笔记\images\image-20210312093847241.png)



## 1. 地址IP

`IP`(Internet Protocol)，用于唯一标识网络通信实体，有两种格式：

+ `IPV4`：4个字节组成（32位），即4个0-255的数字，总计42亿
+ `IPV6`：16个字节（128位），即8个整数，每个整数用4个十六进制数表示

常用IP地址有：

+ `hostAddress`**本地回环地址**：127.0.0.1
+ `hostName`本地主机名：**localhost**

IP地址可以根据使用范围分为，**公网地址**和**内网地址**（局域网），其中公网地址可以通过互联网进行连接，内网地址通常在192.168.0.0-255.255内，只能在本地访问。

### 1.2 域名domain

`域名`用来映射一个实际的IP地址

当使用域名来连接指定主机时，会首先寻找**本地`HOST`文件**，查看域名对应的IP，若不存在会连接**DNS服务器**寻找该域名对应的IP。



## 2. 端口Prot

`Port`端口号用于标识一个设备上运行的不同应用程序/进程，其为一个**2字节的整数**（16位，**0~65535**），根据约定规则有如下分类：

+ **公用端口：0~1023**，被大多数通信协议占用的端口（21FTP，80HTTP，23Telnet，443HTTPS）
+ **注册端口：1024~49151**，分配给各种应用程序和用户（3306Mysql，1521Oracle，8080Tomcat）
+ 动态/私有端口：49152~65535，用于动态分配给应用程序使用或保留

不同的进程端口号不同，若相同会导致**端口冲突**，先占用的进程会优先使用，**后启动的进程将失败**



端口号和IP地址组合得出一个**网络套接字：Socket**（网络节点流）



## 3. 通信协议Protocol

通信协议用于控制网络传输的稳定、速率等，由于网络的分层架构，协议分为**上下层传输**和**同层传输**协议，网络通信不可以跨层传输，其中上下层协议为网络底层和物理封装提供。应用层面网络传输主要使用同层传输协议（**传输层协议**），主要有：

+ `TPC`传输控制协议：重量级，稳定的可靠传输
+ `UDP`用户数据报协议：轻量级，不可靠传输

### 3.1 TCP协议

`TCP`是一种**稳定可靠**的传输协议，使用TCP传输前会**先建立TCP连接，形成数据通道**

1. 传输开始前，使用**三次握手**方式建立连接，点对点通信（两个角色**服务端**和**客户端**）
   1. **客户端发送**syn报文和序列号通知服务端，**询问服务端是否可以收到信息**（C：在吗？）
   2. **服务端发送**syn+ACK和序列号进行确认，**表示收到信息并询问是否收到回应**（S：我在，听得到吗？）
   3. **客户端发送**ACK报文并发送序列号对服务端进行确认，**表示可以收到服务端信息**（C：听得到，事情是-）
2. 可以进行**大量数据**的传输
3. **传输完毕**需要**四次挥手**用于**释放连接**（重量级，效率较）
   1. **客户端发送**Fin+ACK和序列号通知服务端（C：不聊了吧）
   2. **服务端发送**ACK和序列号确认客户端（S：确实很晚了）
   3. **服务端发送**Fin+ACK和序列号通知客户端断开（S：晚安/告知不聊了）
   4. **客户端发送**ACK和序列号确认服务端已经断开（C：晚安/判断是否真的睡了）



### 3.2 UDP协议

`UDP`是一种轻量级的不稳定传输协议，其将数据和源、目标封装成数据包，不需要建立连接即可传输

1. 每个数据包大小限制在64KB以内，当数据量大时会将数据进行拆分
2. **发送方发送**时**不**需要**确认接收方**是否准备完毕
3. **接收方接收**后也**不**向发送方**进行接收确认**
4. 其可以广播传输，**传输完毕后无序释放资源**（轻量级，效率高）



# 二、网络类

## 1. 网络地址InetAddress

`InetAddress`标识一个**网络地址即IP**，其主要**使用内部静态方法来构造对象**

### 1.1 常用方法

| 方法                           | 参数/含义                | 用法                                    |
| ------------------------------ | ------------------------ | --------------------------------------- |
| **InetAddress.getByName()**    | String host **域名或IP** | **指定域名或IP构造**一个InetAddress对象 |
| **InetAddress.getLocalHost()** | 本地                     | **本机名和IP构造**的InetAddress对象     |
| I.getHostName()                | 域名                     | 获取**对象I的域名/主机名**              |
| I.getHostAddress()             | 地址                     | 获取**对象I的IP地址**                   |



## 2. TCP传输

TPC是一种面向连接的可靠的网络传输，必须**先经历三次握手建立连接**，才可以进行通信，是一种**持久性通信**，分为客户端和服务端，其中客户端必须建立连接才可通信（**服务端必须先于客户端启动**），JAVA中只有有以下类：



### 2.1 客户端节点Socket

`Socket`是套接字，是网络传输之间的**节点**，使用端口和`InetAddress`构造，可以从中获取其对应节点的**输入和输出流**

#### 2.2.1 常用方法

| 方法/构造           | 参数/含义                                      | 用法                                            |
| ------------------- | ---------------------------------------------- | ----------------------------------------------- |
| **new Socket()**    | address: 地址InetAddress<br>port：**目标端口** | 使用地址和端口**构造Socket对象**                |
| s.getInetAddress()  | **获取地址对象**                               | 获取**对象s的地址对象**                         |
| s.getOutputStream() | **输出流**                                     | 获取**对象s的输出流**                           |
| s.getInputStream()  | **输人流**                                     | 获取**对象s的输入流**                           |
| s.shutdownOutput()  | **声明单次写入结束**                           | **声明对象s本次写入操作完成**<br>否则会一直阻塞 |
| s.shutdownInput()   | 声明单词读取结束                               | 声明本次读取结束<br>否则会一直阻塞              |



### 2.2 服务端ServerSocket

`ServerSocket`用于监听本机上的某一个端口的网络数据即**服务端**，并可以从中获取`Socket`对象。

#### 2.2.1 常用方法

| 方法/构造            | 参数/含义            | 用法                             |
| -------------------- | -------------------- | -------------------------------- |
| **new ServerSocket** | port：**待监听端口** | 使用端口**构造ServerSocket对象** |
| s.accept()           | 接收Socket           | 接收数据**获取接收的节点Socket** |



### 2.3 即时通讯案例

[即时通讯](../src/cn/hll520/java/iosample/netconection/TPCMessage.java)




## 3. UDP传输

UDP是面向数据包的不可靠的轻量级的传输方式，发送和接收端**无需建立连接**，也不互相确实是否传输成功，各自只负责发送和接收。

### 3.1 数据报DatagramPacket

`DatagramPacket`是数据包类，用于向封装UDP数据包（根据UDP传输规则，数据包中包含了目标地址和端口）或从中取出数据。

#### 3.1.2 常用方法

| 方法/构造            | 含义/参数                                                    | 用法                                                   |
| -------------------- | ------------------------------------------------------------ | ------------------------------------------------------ |
| new DatagramPacket() | `bytes[]`待传输数据<br>封装从待传数据`offset`位置开始<br>到`length`长度为止的数据<br>`InetAddress`目标地址<br>`int port`目标端口 | **构造**包含数据和目的地的**数据包**<br>**常用于发送** |
| new DatagramPacket() | `bytes[]`数据缓存<br/>封装从待传数据`offset`位置开始<br/>到`length`长度为止的数据<br/> | 使用缓存构造数据包<br>**常用于接收，数据会写入缓存中** |
| p.getLength()        | 数据包大小                                                   | 获取**数据包数据大小**（非缓存大小）                   |
| p.getData()          | 数据包数据                                                   | 获取数据包中的**缓存数据**（全部）                     |

####  

### 3.2 连接DatagramSocket

`DatagramSocket`是UDP的连接类，UDP没有客户端和服务端之分，根据其功能分为**发送**和**接收**，一般发送端使用无参构造，**目标的地址填写在数据包中**，同时收发或接收端使用端口构造。

#### 3.2.1 常用方法

| 方法/构造             | 参数/含义                      | 用法                                     |
| --------------------- | ------------------------------ | ---------------------------------------- |
| new DatagramSocket()  |                                | 构造一个UDP对象                          |
| new DatagramSocket(); | `int port`端口                 | 构造一个监听指定端口的UDP对象            |
| s.send()              | `DatagramPacket`待发送数据包   | **发送一个数据包**（需要包含目标和端口） |
| s.receive()           | `DatagramPacket`接收写入数据包 | **接收**并将**数据写入指定数据包**       |





### 3.3 使用案例

```java
public class UDPSample {

    @Test
    public void send() throws Exception {
        // UDP 客户端
        DatagramSocket datagramSocket = new DatagramSocket();
        byte[] bytes = "UPD send ok".getBytes();
        InetAddress localHost = InetAddress.getLocalHost();
        // 封装数据包
        DatagramPacket datagramPacket = new DatagramPacket(bytes,0,bytes.length,
                localHost,8888);
        // 发送数据
        datagramSocket.send(datagramPacket);
        // 关闭UDP 连接
        datagramSocket.close();
    }

    @Test
    public void receiver() throws Exception {
        // 实例化 UDP 监听客户端
        DatagramSocket socket=new DatagramSocket(8888);
        byte[] bytes=new byte[1024];

        DatagramPacket datagramPacket = new DatagramPacket(bytes,0,bytes.length);
        // 接收数据
        socket.receive(datagramPacket);

        // 显示
        System.out.println(new String(datagramPacket.getData(),datagramPacket.getLength()));

        socket.close();
    }
}
```





## 4. URL

`URL`**统一资源定位符**，用来标识网络上的**某一资源**的地址，只需要使用URL即可获取到网络上的指定资源，通常URL格式为：

```sh
<传输协议>://<主机号或域名>:<端口号>/<文件资源路径>#[片段名]?[参数k=v&k2=v2]
```



### 4.1 URL类

在JAVA中**URL类与之对应**，默认**构造使用`URL`字符串**

| 方法/构造            | 含义/参数                       | 用法                                                         |
| -------------------- | ------------------------------- | ------------------------------------------------------------ |
| new URL()            | `String URL`URL字符串           | 使用一个**资源地址构造URL对象**                              |
| url.getProtocol()    | 获取协议                        | 获取url对象的**传输协议**                                    |
| url.getHost()        | 获取IP或域名                    | 获取url对象的**IP或域名**                                    |
| url.getPort()        | 获取端口号                      | 获取url对象的**端口号**<br>**省略端口将返回-1**（如HTTP省略80） |
| url.getPath()        | 获取资源路径                    | 获取url对象的**资源路径**（**包括`/`**）                     |
| url.getFile()        | 获取文件名                      | 获取url对象的文件名<br>默认为**端口号后的全部**              |
| url.getQuery()       | 获取查询参数                    | 获取url对象的**查询参数**<br>**返回格式为`k=v&k1=v2&k2=v2`** |
| url.openConnection() | **获取链接对象**`URLConnection` | 获取该**URL对应的链接对象**                                  |



### 4.2 URL链接URLConnection

`URLConnection`是JAVA中URL链接的**接口**，主要使用`HttpURLConnection`或`HttpsURLConnection`接口作为其派生接口来使用，以下以`HTTPURLConnection`来列举常用方法

#### 4.2.1 常用方法

| 方法                 | 含义           | 用法                                  |
| -------------------- | -------------- | ------------------------------------- |
| cn.connect()         | **建立连接**   | **连接URL指向的资源**（void）         |
| cn.getInputStream()  | **获取输入流** | 获取URL连接的输入流（需要先建立连接） |
| cn.getOutputStream() | **获取输出流** | 获取URL连接的输出流（需要先建立连接） |
| cn.disconnect()      | **关闭连接**   | 关闭URL连接                           |



### 4.3 使用案例

```java
public void connect() throws Exception {
    URL url=new URL("http://wtushop.hll520.cn:8080/");

    // 获取链接对象
    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

    // 进行链接
    urlConnection.connect();

    // 获取输入流
    InputStream inputStream = urlConnection.getInputStream();
    // 写入
    FileOutputStream fileOutputStream=new FileOutputStream("src/res/wtu.html");
    int len;
    byte[] bytes=new byte[1024];
    while((len=inputStream.read(bytes))!=-1)
        fileOutputStream.write(bytes,0,len);

    // 关闭流
    fileOutputStream.close();
    inputStream.close();
    urlConnection.disconnect();
}
```

