package cn.hll520.java.iosample.netconection.udp;

import org.junit.Test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-03-12-16:27
 * @since 2021-03-12-16:27
 */
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
