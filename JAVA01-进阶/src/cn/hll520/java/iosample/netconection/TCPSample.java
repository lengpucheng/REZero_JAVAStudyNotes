package cn.hll520.java.iosample.netconection;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-03-12-10:45
 * @since 2021-03-12-10:45
 */
public class TCPSample {

    @Test
    public void client() throws Exception {
        // 1. 构建 地址对象 （类似于IO的File)
        InetAddress ip = InetAddress.getByName("127.0.0.1");
        // 2. 使用地址对象实例化套接字流 （IO中的节点流File）
        Socket socket = new Socket(ip, 8899);
        // 3. 获取其的处理流
        OutputStream outputStream = socket.getOutputStream();

        // 4. 写入
        outputStream.write("this is client".getBytes());

        // 5. 关闭资源
        outputStream.close();
        socket.close();

    }

    @Test
    public void server() throws Exception {
        // 1. 构建服务对象 监听某个端口
        ServerSocket serverSocket = new ServerSocket(8899);

        // 2. 接收数据 获取套接字流对象（节点）
        Socket accept = serverSocket.accept();
        // 3. 获取处理流
        InputStream inputStream = accept.getInputStream();

        // 使用处理流处理避免乱码
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[15];
        int len;
        // 读取并写入数组流缓存 避免乱码
        while ((len = inputStream.read(bytes)) != -1)
            outputStream.write(bytes, 0, len);

        System.out.println(outputStream.toString());
        System.out.println(accept.getInetAddress().getHostName());


        // 关闭资源
        outputStream.close();
        inputStream.close();
        accept.close();
        serverSocket.close();

    }
}
