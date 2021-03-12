package cn.hll520.java.iosample.netconection.udp;

import org.junit.Test;

import javax.net.ssl.HttpsURLConnection;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-03-12-17:03
 * @since 2021-03-12-17:03
 */
public class URLSample {

    @Test
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

    @Test
    public void test() throws Exception {
        URL url=new URL("https://www.bilibili.com/video/BV1Kb411W75N?p=629&spm_id_from=pageDriver");

        // 协议
        System.out.println(url.getProtocol());
        // 主机名/域名
        System.out.println(url.getHost());
        // 端口
        System.out.println(url.getPort());
        // 文件路径
        System.out.println(url.getPath());
        // 文件名
        System.out.println(url.getFile());
        // 查询参数
        System.out.println(url.getQuery());

    }
}
