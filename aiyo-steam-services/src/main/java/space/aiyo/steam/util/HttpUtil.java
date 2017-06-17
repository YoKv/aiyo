package space.aiyo.steam.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * TODO 连接池
 * Created by yo on 2017/5/17.
 */
public class HttpUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

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
        int status = connection.getResponseCode();
        if (status == 429) {
            logger.warn("HTTP status 429, Too Many Requests (太多请求)");
            return "";
        } else if (status == 503) {
            logger.warn("HTTP status 503, 服务器目前无法使用（由于超载或停机维护）");
            return "";
        }
        // 取得输入流，并使用Reader读取 暂时使用utf-8
//        The ISO639-1 language code for the language all tokenized strings should be returned in.
//                Not all strings have been translated to every language.
//        If a language does not have a string, the English string will be returned instead.
//        If this parameter is omitted the string token will be returned for the strings.
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                connection.getInputStream(), "utf-8"));

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
