package business;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import net.HttpUtil;

import java.io.IOException;

public class GEOUtil {
    private static final String MAP_GEOCODER_API_URL = "http://api.business.baidu.com/geocoder/v2/";    //地图API地址
    private static final String AK = "";                        //key，需申请产生
    private static String output = "json";                                                        //输出格式，可选json或xml
    private static Integer poi = 0;                                                                //是否显示附近poi，1为显示，0为不显示
//	private static String callback = "renderReverse";											//返回json时的callback函数

    /**
     * 根据经纬度反向解析地址，百度地图geocoder
     *
     * @param latitude  纬度
     * @param longitude 经度
     * @return String[]
     */
    public static String[] GetAddr(String latitude, String longitude) {
        String addr[] = new String[3];
        String requestUrl = MAP_GEOCODER_API_URL + "?ak=" + AK
                + "&output=" + output
                + "&poi=" + poi
//						+ "&callback=" + callback
                + "&location=%base,%base";
        String fomatedRequestUrl = String.format(requestUrl, latitude, longitude);

        String data;
        try {
            data = HttpUtil.sendGet(fomatedRequestUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        if (data != null) {
            JSONObject json = JSON.parseObject(data);
            JSONObject addressComponent = json.getJSONObject("result").getJSONObject("addressComponent");
            addr[0] = addressComponent.getString("province");
            addr[1] = addressComponent.getString("city");
            addr[2] = addressComponent.getString("district");

        }
        return addr;
    }


}