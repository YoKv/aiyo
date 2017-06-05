package space.aiyo.steam.services;

import space.aiyo.steam.entity.DotaHeroEntity;

import java.util.List;

/**
 * dota英雄相关
 * Created by yo on 2017/5/27.
 */
public interface DotaHeroService {
    /**
     * 从steam平台获取英雄信息
     *
     * @return 英雄列表
     */
    List<DotaHeroEntity> getHeroFromSteamApi();

    /**
     * 新建或更新单条英雄信息
     */
    void saveHero(DotaHeroEntity hero);

    /**
     * 从steam平台新建或更新英雄信息
     */
    void saveHeroFromSteamApi();

    /**
     * 获取英雄列表
     *
     * @return 英雄列表
     */
    List<DotaHeroEntity> getHeroes();
}
