package business;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GEOUtil {
    private static final String MAP_GEOCODER_API_URL = "net://api.business.baidu.com/geocoder/v2/";    //地图API地址
    private static final String AK = "";                        //key，需申请产生
    private static String output = "json";                                                        //输出格式，可选json或xml
    private static Integer poi = 0;                                                                //是否显示附近poi，1为显示，0为不显示
//	private static String callback = "renderReverse";											//返回json时的callback函数

    public static void main(String[] args) {
        String[] addr = GetAddr("3.8616", "19.53970");
        for (String s : addr) {
            System.out.println(s);
        }

        // getCoordinate("中国");
    }

    /**
     * 根据经纬度反向解析地址，百度地图geocoder
     *
     * @param latitude  纬度
     * @param longitude 经度
     * @return
     */
    public static String[] GetAddr(String latitude, String longitude) {
        String addr[] = new String[3];
        String requestUrl = MAP_GEOCODER_API_URL + "?ak=" + AK
                + "&output=" + output
                + "&poi=" + poi
//						+ "&callback=" + callback
                + "&location=%base,%base";

        String fomatedRequestUrl = String
                .format(requestUrl, latitude, longitude);
        URL myURL = null;
        URLConnection httpsConn = null;
        String data = null;
        try {
            myURL = new URL(fomatedRequestUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        try {
            httpsConn = (URLConnection) myURL.openConnection();
            if (httpsConn != null) {
                InputStreamReader insr = new InputStreamReader(
                        httpsConn.getInputStream(), "UTF-8");
                BufferedReader br = new BufferedReader(insr);
                String line;
                if ((line = br.readLine()) != null) {
                    data = line;
                }
                insr.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        if (data != null) {
            System.out.println(data);
            JSONObject json = JSON.parseObject(data);
            JSONObject addressComponent = json.getJSONObject("result").getJSONObject("addressComponent");
            addr[0] = addressComponent.getString("province");
            addr[1] = addressComponent.getString("city");
            addr[2] = addressComponent.getString("district");

        }
        return addr;
    }


}