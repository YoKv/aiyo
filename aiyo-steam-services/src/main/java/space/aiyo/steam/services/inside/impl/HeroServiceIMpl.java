package space.aiyo.steam.services.inside.impl;

import com.alibaba.fastjson.JSON;
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
        String result = "";
        String url = SteamContsant.DOTA_IEcon_PATH + "/GetHeroes/v1?key=" + SteamContsant.STEAM_KEY;
        try {
            result = HttpUtil.sendGet(url);
        } catch (IOException e) {

        }
        JSONObject resultObject = JSON.parseObject(result);
        String heroes = (String) resultObject.get("heroes");
        List<Dota2HeroEntity> heroeEntities = JSON.parseArray(heroes, Dota2HeroEntity.class);
        System.out.println(heroeEntities);
        return heroeEntities;
    }

}
