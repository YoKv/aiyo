package space.aiyo.steam.services;

import space.aiyo.database.mongoDB.entity.DotaHeroEntity;

import java.util.List;

/**
 * dota英雄相关
 * Created by yo on 2017/5/27.
 */
public interface DotaHeroService {
    /**
     * 从steam平台新建或更新英雄信息
     */
    List<DotaHeroEntity> saveHeroFromSteamApi();

    List<DotaHeroEntity> getSteamHeroes();

    List<DotaHeroEntity> getHeroes();

    DotaHeroEntity getHero(Integer id, String name, String localizedName);

    List<DotaHeroEntity> save(List<DotaHeroEntity> heroes);

}
