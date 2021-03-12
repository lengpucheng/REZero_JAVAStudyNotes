package cn.hll520.java.iosample.netconection;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-03-12-9:53
 * @since 2021-03-12-9:53
 */
public class IPSample {
    @Test
    public void test() throws UnknownHostException {
        InetAddress wtu = InetAddress.getByName("wtu.hll520.cn");
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(wtu);
        System.out.println(localHost);
        System.out.println(wtu.getHostName());
        System.out.println(wtu.getHostAddress());
    }
}
