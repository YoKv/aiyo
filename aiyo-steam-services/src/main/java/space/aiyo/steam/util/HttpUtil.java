package space.aiyo.steam.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by yo on 2017/5/17.
 */
public class HttpUtil {
    /**
     * 模拟发送get请求
     */
    public static String sendGet(String url) throws IOException {
        return send(url, "GET");
    }

    /**
     * 模拟发送post请求
     */
    public static String sendPost(String url) throws IOException {
        return send(url, "POST");
    }

    private static String send(String urlStr, String type) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // 服务器连接
        if ("POST".equals(type)) {
            connection.setRequestMethod("POST");// 提交模式
            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
            // conn.setReadTimeout(2000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            connection.setDoOutput(true);
            connection.setDoInput(true);
        }

        connection.connect();
        // 取得输入流，并使用Reader读取
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));

        StringBuilder sb = new StringBuilder();
        String lines;
        while ((lines = reader.readLine()) != null) {
            sb.append(lines);
        }
        reader.close();
        // 断开连接
        connection.disconnect();
        return sb.toString();
    }

}
