package space.aiyo.test;

import org.junit.Test;
import space.aiyo.base.util.HttpUtil;

import java.io.IOException;

/**
 * Created by yo on 2017/5/17.
 */
public class SteamApiTest {

    @Test
    public void test() {
        String result;
        String urlStr = "http://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/v1?match_id=3184216260&key=B012552DE5DD0A220085EE8B21FBEC32";
        try {
            result = HttpUtil.sendGet(urlStr);
            System.out.print(result);
        } catch (IOException e) {

        }

    }
}
