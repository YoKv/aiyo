package space.aiyo.core.services;

import space.aiyo.core.entity.DotaHeroEntity;

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

    /**
     * 从steam平台获取英雄信息
     */
    List<DotaHeroEntity> getSteamHeroes();

    /**
     * 从数据库获取所有英雄信息
     */
    List<DotaHeroEntity> getHeroes();

    /**
     * 通过id,全名，中文名查找一个英雄
     *
     * @param id            id
     * @param name          全名
     * @param localizedName 中文名
     * @return DotaHeroEntity
     */
    DotaHeroEntity getHero(Integer id, String name, String localizedName);

    /**
     * 保存
     */
    List<DotaHeroEntity> save(List<DotaHeroEntity> heroes);

}
