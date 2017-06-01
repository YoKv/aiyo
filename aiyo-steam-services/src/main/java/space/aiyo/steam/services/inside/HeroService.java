package space.aiyo.steam.services.inside;

import space.aiyo.steam.entity.Dota2HeroEntity;

import java.util.List;

/**
 * dota英雄相关
 * Created by yo on 2017/5/27.
 */
public interface HeroService {
    /**
     * 从steam平台获取英雄信息
     *
     * @return
     */
    List<Dota2HeroEntity> getHeroFromSteamApi();

    /**
     * 新建或更新单条英雄信息
     */
    void saveHero(Dota2HeroEntity hero);

    /**
     * 从steam平台新建或更新英雄信息
     */
    void saveHeroFromSteamApi();
}
