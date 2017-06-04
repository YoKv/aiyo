package space.aiyo.steam.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import space.aiyo.steam.contsant.SteamContsant;
import space.aiyo.steam.entity.Dota2HeroEntity;
import space.aiyo.steam.enums.SteamApiEnum;
import space.aiyo.steam.repository.Dota2HeroRepository;
import space.aiyo.steam.services.HeroService;
import space.aiyo.steam.util.HttpUtil;

import java.io.IOException;
import java.util.List;

/**
 * 英雄相关服务接口
 * Created by yo on 2017/5/27.
 */
@Component
public class HeroServiceImpl implements HeroService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Dota2HeroRepository repository;

    @Autowired
    public HeroServiceImpl(Dota2HeroRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Dota2HeroEntity> getHeroFromSteamApi() {
        String returnStr = "";
        String url = SteamContsant.STEAM_API_PATH + SteamApiEnum.GetHeroes.getUrl() + "?language=zh&key=" + SteamContsant.STEAM_KEY;
        try {
            returnStr = HttpUtil.sendGet(url);
        } catch (IOException e) {
            logger.info("调用steam接口失败: " + e.toString());
        }
        JSONObject result = (JSONObject) JSON.parseObject(returnStr).get("result");
        JSONArray heroesArray = result.getJSONArray("heroes");

        return JSON.parseArray(heroesArray.toJSONString(), Dota2HeroEntity.class);
    }

    @Override
    public void saveHero(Dota2HeroEntity hero) {
        try {
            repository.save(hero);
        } catch (Exception e) {
            logger.info("英雄数据存入数据库失败: " + e.toString());
        }
    }

    @Override
    public void saveHeroFromSteamApi() {
        List<Dota2HeroEntity> heroes = getHeroFromSteamApi();
        for (Dota2HeroEntity hero :
                heroes) {
            saveHero(hero);
        }
    }

    @Override
    public List<Dota2HeroEntity> getHeroes() {
        return repository.findAll();
    }
}
