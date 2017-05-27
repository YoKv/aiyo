package space.aiyo.steam.services.inside.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import space.aiyo.steam.contsant.SteamContsant;
import space.aiyo.steam.entity.Dota2HeroEntity;
import space.aiyo.steam.services.inside.HeroService;
import space.aiyo.steam.util.HttpUtil;

import java.io.IOException;
import java.util.List;

/**
 * Created by yo on 2017/5/27.
 */
@Component
public class HeroServiceIMpl implements HeroService{
    @Override
    public List<Dota2HeroEntity> getHeroFromSteamApi() {
        String returnStr = "";
        String url = SteamContsant.DOTA_IEcon_PATH + "/GetHeroes/v1?key=" + SteamContsant.STEAM_KEY;
        try {
            returnStr = HttpUtil.sendGet(url);
        } catch (IOException e) {
            //TODO log
        }
        JSONObject result = (JSONObject)JSON.parseObject(returnStr).get("result");

        JSONArray heroesArray =result.getJSONArray("heroes");
        // TODO 转换失败
        List<Dota2HeroEntity> heroes = JSON.parseArray(heroesArray.toJSONString(), Dota2HeroEntity.class);


        System.out.println("HeroServiceIMpl"+heroes);
        return heroes;
    }

}
