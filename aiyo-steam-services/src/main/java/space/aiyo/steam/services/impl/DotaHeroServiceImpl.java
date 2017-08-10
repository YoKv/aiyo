package space.aiyo.steam.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.aiyo.database.mongoDB.dao.DotaHeroDao;
import space.aiyo.database.mongoDB.entity.DotaHeroEntity;
import space.aiyo.steam.contsant.SteamContsant;
import space.aiyo.steam.enums.SteamApiEnum;
import space.aiyo.steam.services.DotaHeroService;
import space.aiyo.util.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 英雄相关服务接口
 * Created by yo on 2017/5/27.
 */
@Service
public class DotaHeroServiceImpl extends DotaHeroDao implements DotaHeroService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public void saveHeroFromSteamApi() {
        List<DotaHeroEntity> heroes = getHeroFromSteamApi();
        saveAll(heroes);
    }

    /**
     * 接口不稳定，处理机制 TODO
     *
     */
    private List<DotaHeroEntity> getHeroFromSteamApi() {
        String returnStr = "";
        String url = SteamContsant.STEAM_API_PATH + SteamApiEnum.GET_HEROES.getUrl() + "?language=zh&key=" + SteamContsant.STEAM_KEY;
        try {
            returnStr = HttpUtil.sendGet(url);
        } catch (IOException e) {
            logger.info("调用steam接口失败: " + e.toString());
        }
        if (!returnStr.isEmpty()) {
            JSONObject result = (JSONObject) JSON.parseObject(returnStr).get("result");
            JSONArray heroesArray = result.getJSONArray("heroes");
            return JSON.parseArray(heroesArray.toJSONString(), DotaHeroEntity.class);
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public List<DotaHeroEntity> getHeroes() {
        return findAll();
    }
    @Override
    public List<DotaHeroEntity> saveAll(List<DotaHeroEntity> heroes) {
        return saveAll(heroes);
    }

}
