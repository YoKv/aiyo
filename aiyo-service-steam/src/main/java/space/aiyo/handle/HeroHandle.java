package space.aiyo.handle;

import entity.dota.HeroEntity;
import java.util.List;

/**
 * CREATE BY Yo ON 2017/11/19 14:36
 */
public interface HeroHandle {

  /**
   * 从steam平台新建或更新英雄信息
   */
  List<HeroEntity> saveHeroFromSteamApi();

  /**
   * 从steam平台获取英雄信息
   */
  List<HeroEntity> getSteamHeroes();

  /**
   * 从数据库获取所有英雄信息
   */
  List<HeroEntity> getHeroes();

  /**
   * 通过id,全名，中文名查找一个英雄
   *
   * @param id id
   * @param name 全名
   * @param localizedName 中文名
   * @return HeroEntity
   */
  HeroEntity getHero(Integer id, String name, String localizedName);

  /**
   * 保存
   */
  List<HeroEntity> save(List<HeroEntity> heroes);

}
