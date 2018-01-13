package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * TODO 连接池
 * Created by yo on 2017/5/17.
 */
public class HttpUtil {

  private static final Logger logger = LogManager.getLogger(HttpUtil.class);

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
    if ("POST".equals(type)) {
      connection.setRequestMethod("POST");// 提交模式
      connection.setDoOutput(true);
      connection.setDoInput(true);
    }
    connection.connect();
    int status = connection.getResponseCode();
    if (status == 429) {
      logger.warn("HTTP status 429, Too Many Requests (频繁请求)");
      return null;
    } else if (status == 503) {
      logger.warn("HTTP status 503, 服务器目前无法使用（由于超载或停机维护）");
      return null;
    }

    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(connection.getInputStream(), "utf-8"))) {
      String result = reader.lines().collect(Collectors.joining());
      // 断开连接
      connection.disconnect();
      return result;
    }
  }

}
