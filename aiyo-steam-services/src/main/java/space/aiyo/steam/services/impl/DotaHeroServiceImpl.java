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
public class DotaHeroServiceImpl implements DotaHeroService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    final private DotaHeroDao heroDao;

    @Autowired
    public DotaHeroServiceImpl(DotaHeroDao heroDao) {
        this.heroDao = heroDao;
        Inner.heroDao = heroDao;
    }

    /**
     * 从steam获取英雄信息，并保存
     *
     * @return List<DotaHeroEntity>
     */
    @Override
    public List<DotaHeroEntity> saveHeroFromSteamApi() {
        List<DotaHeroEntity> heroes = Inner.getHeroFromSteamApi();
        return heroDao.saveAll(heroes);
    }

    @Override
    public List<DotaHeroEntity> getHeroes() {
        return heroDao.findAll();
    }

    @Override
    public List<DotaHeroEntity> save(List<DotaHeroEntity> heroes) {
        return heroDao.saveAll(heroes);
    }

    /**
     * 提供静态方法
     */
    private static class Inner {
        private static Logger logger = LoggerFactory.getLogger(Inner.class);
        static DotaHeroDao heroDao;
        /**
         * 从steam获取英雄信息
         *
         * @return List<DotaHeroEntity>
         */
        private static List<DotaHeroEntity> getHeroFromSteamApi() {
            String returnStr = "";
            StringBuilder url = new StringBuilder();
            url.append(SteamContsant.STEAM_API_PATH).append(SteamApiEnum.GET_HEROES.getUrl());
            url.append("?language=zh&key=").append(SteamContsant.STEAM_KEY);
            try {
                returnStr = HttpUtil.sendGet(url.toString());
            } catch (IOException e) {
                logger.warn("调用steam接口失败: " + e.toString());
            }
            if (!returnStr.isEmpty()) {
                JSONObject result = (JSONObject) JSON.parseObject(returnStr).get("result");
                JSONArray heroesArray = result.getJSONArray("heroes");
                return JSON.parseArray(heroesArray.toJSONString(), DotaHeroEntity.class);
            } else {
                return new ArrayList<>();
            }
        }

    }

}
