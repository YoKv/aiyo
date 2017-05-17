package space.aiyo.steam.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by yo on 2017/5/17.
 */
public class HttpUtil {
    /**
     * 模拟发送get请求
     */
    public static String sendGet(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection connection =(HttpURLConnection) url.openConnection();
        // 服务器连接
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
    /**
     * 模拟发送post请求
     */
    public  static String sendPost(){

        return "";
    }
    public static void main(String[] args) {
        try{
            HttpUtil.sendGet("s");
        }catch (IOException e){

        }
    }
}
