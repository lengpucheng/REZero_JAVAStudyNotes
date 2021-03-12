package cn.hll520.java.iosample.netconection;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-03-12-11:22
 * @since 2021-03-12-11:22
 */
public class TCPFileSample {

    @Test
    public void client() throws Exception {
        // 1. 初始化地址
        InetAddress inetAddress = InetAddress.getLocalHost();
        // 2. socket 实例化
        Socket socket = new Socket(inetAddress, 8888);
        // 3. 获取流
        OutputStream outputStream = socket.getOutputStream();
        // 获取待发送文件
        FileInputStream ifs = new FileInputStream("src/res/lpc.asc");
        int len;
        byte[] bytes = new byte[1024];
        // 读取 同时写入
        while ((len = ifs.read(bytes)) != -1)
            outputStream.write(bytes, 0, len);

        // 声明输出写入完成
        socket.shutdownOutput();

        // 接收声明
        InputStream inputStream = socket.getInputStream();
        ByteArrayOutputStream bao=new ByteArrayOutputStream();
        while ((len=inputStream.read(bytes))!=-1)
            bao.write(bytes,0,len);
        // 输出
        System.out.println(bao.toString());
        // 5. 关闭
        ifs.close();
        outputStream.close();
        socket.close();
    }

    @Test
    public void server() throws Exception {
        // 1. 监听端口 实例化server
        ServerSocket serverSocket = new ServerSocket(8888);
        // 2. 接收
        Socket socket = serverSocket.accept();
        // 3. 获取输入流
        InputStream inputStream = socket.getInputStream();
        // 文件输出
        FileOutputStream ofs=new FileOutputStream("src/res/down.asc");
        int len;
        byte[] bytes=new byte[1024];
        while((len=inputStream.read(bytes))!=-1)
            ofs.write(bytes,0,len);

        // 返回声明
        OutputStream or = socket.getOutputStream();
        or.write("tcp accept file ok!".getBytes());
        socket.shutdownOutput();

        // 5. 关闭
        or.close();
        ofs.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
    }

}
