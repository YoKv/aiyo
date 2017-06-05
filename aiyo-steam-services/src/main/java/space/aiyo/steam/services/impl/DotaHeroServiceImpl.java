package space.aiyo.steam.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.aiyo.steam.contsant.SteamContsant;
import space.aiyo.steam.entity.DotaHeroEntity;
import space.aiyo.steam.enums.SteamApiEnum;
import space.aiyo.steam.repository.DotaHeroRepository;
import space.aiyo.steam.services.DotaHeroService;
import space.aiyo.steam.util.HttpUtil;

import java.io.IOException;
import java.util.List;

/**
 * 英雄相关服务接口
 * Created by yo on 2017/5/27.
 */
@Service
public class DotaHeroServiceImpl implements DotaHeroService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DotaHeroRepository repository;

    @Autowired
    public DotaHeroServiceImpl(DotaHeroRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DotaHeroEntity> getHeroFromSteamApi() {
        String returnStr = "";
        String url = SteamContsant.STEAM_API_PATH + SteamApiEnum.GetHeroes.getUrl() + "?language=zh&key=" + SteamContsant.STEAM_KEY;
        try {
            returnStr = HttpUtil.sendGet(url);
        } catch (IOException e) {
            logger.info("调用steam接口失败: " + e.toString());
        }
        JSONObject result = (JSONObject) JSON.parseObject(returnStr).get("result");
        JSONArray heroesArray = result.getJSONArray("heroes");

        return JSON.parseArray(heroesArray.toJSONString(), DotaHeroEntity.class);
    }

    @Override
    public void saveHero(DotaHeroEntity hero) {
        try {
            repository.save(hero);
        } catch (Exception e) {
            logger.info("英雄数据存入数据库失败: " + e.toString());
        }
    }

    @Override
    public void saveHeroFromSteamApi() {
        List<DotaHeroEntity> heroes = getHeroFromSteamApi();
        for (DotaHeroEntity hero :
                heroes) {
            saveHero(hero);
        }
    }

    @Override
    public List<DotaHeroEntity> getHeroes() {
        return repository.findAll();
    }
}
