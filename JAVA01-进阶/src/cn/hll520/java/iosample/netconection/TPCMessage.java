package cn.hll520.java.iosample.netconection;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 描述：消息发送与读取
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-03-12-15:36
 * @since 2021-03-12-15:36
 */
public class TPCMessage {
    public static void main(String[] args) throws Exception {
        client();
    }

    @Test
    public void server() throws Exception {
        // 1. 实例化监听
        ServerSocket serverSocket = new ServerSocket(8088);
        boolean off = true;

        while (off) {
            // 2. 接收
            Socket socket = serverSocket.accept();
            // 3. 获取流
            InputStream inputStream = socket.getInputStream();
            // 处理接收为msg对象
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Msg msg = (Msg) objectInputStream.readObject();
            // 声明读取完毕
            socket.shutdownInput();

            // 显示消息
            showMsg(msg);
            // 判断是否退出
            if ("exit".equals(msg.getMsg())) {
                off = false;
                msg.setMsg("server close");
            } else {
                // 处理并返回
                msg.setMsg("已经收到，转换后为:\n" + msg.getMsg().toUpperCase());
            }
            msg.setTime(LocalDateTime.now());

            // 返回
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(msg);
            // 声明写入完毕
            socket.shutdownOutput();

            // 关闭一次会话
            objectOutputStream.close();
            objectInputStream.close();
            socket.close();
        }
        // 关闭服务监听
        serverSocket.close();
    }


    public static void client() throws Exception {
        // host
        InetAddress localHost = InetAddress.getLocalHost();
        boolean off = true;
        InputStreamReader reader=new InputStreamReader(System.in);
        BufferedReader bfr=new BufferedReader(reader);
        while (off) {
            String s = bfr.readLine();
            Socket socket = new Socket(localHost, 8088);
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            // 写入
            oos.writeObject(new Msg(s, LocalDateTime.now()));
            // 声明写入完毕
            socket.shutdownOutput();

            // 接收
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            Msg msg = (Msg) ois.readObject();
            // 标识读取完毕
            socket.shutdownInput();
            // 显示
            showMsg(msg);
            off = !"server close".equals(msg.getMsg());

            // 关闭
            oos.close();
            ois.close();
            socket.close();
        }
        bfr.close();
    }

    /**
     * 显示消息
     *
     * @param msg 消息
     */
    public static void showMsg(Msg msg) {
        System.out.println("__________________");
        System.out.println("name:" + msg.getName() + "  |  " + msg.getTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println(msg.getMsg());
    }


}


/**
 * 消息
 */
class Msg implements Serializable {
    public static final long serialVersionUID = 42222L;
    private String name;
    private String msg;
    private LocalDateTime time;

    public Msg(String msg, LocalDateTime time) {
        this.msg = msg;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}