package space.aiyo.core.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.aiyo.core.dao.DotaHeroDao;
import space.aiyo.core.entity.DotaHeroEntity;
import space.aiyo.val.contsant.SteamContsant;
import space.aiyo.val.enums.SteamApiEnum;
import space.aiyo.core.services.DotaHeroService;
import net.HttpUtil;

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
        InnerMethod.setHeroDao(heroDao);
    }

    /**
     * 从steam获取英雄信息，并保存
     *
     * @return List<DotaHeroEntity>
     */
    @Override
    public List<DotaHeroEntity> saveHeroFromSteamApi() {
        List<DotaHeroEntity> heroes = InnerMethod.getHeroFromSteamApi();
        return heroDao.saveAll(heroes);
    }

    @Override
    public List<DotaHeroEntity> getSteamHeroes() {
        return InnerMethod.getHeroFromSteamApi();
    }

    @Override
    public List<DotaHeroEntity> getHeroes() {
        return heroDao.findAll();
    }

    @Override
    public DotaHeroEntity getHero(Integer id, String name, String localizedName) {
        return heroDao.findByIdOrNameOrLocalizedName(id, name, localizedName);
    }

    @Override
    public List<DotaHeroEntity> save(List<DotaHeroEntity> heroes) {
        return heroDao.saveAll(heroes);
    }

    /**
     * 提供静态方法的内部静态类
     */
    private static class InnerMethod {
        private static Logger logger = LoggerFactory.getLogger(InnerMethod.class);
        private static DotaHeroDao heroDao;

        /**
         * 从steam获取英雄信息
         *
         * @return List<DotaHeroEntity>
         */
        private static List<DotaHeroEntity> getHeroFromSteamApi() {
            String returnStr = "";
            StringBuilder url = new StringBuilder();
            url.append(SteamContsant.STEAM_API_PATH).append(SteamApiEnum.GET_HEROES.getUrl());
            //TODO 英雄的详细信息
            url.append("?language=zh&itemizedonly=").append(true).append("&key=").append(SteamContsant.STEAM_KEY);
            try {
                returnStr = HttpUtil.sendGet(url.toString());
//                System.out.println(returnStr);
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

        private static void setHeroDao(DotaHeroDao heroDao) {
            InnerMethod.heroDao = heroDao;
        }
    }

}
