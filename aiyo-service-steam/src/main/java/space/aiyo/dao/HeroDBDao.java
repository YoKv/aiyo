package space.aiyo.dao;

import entity.dota.HeroEntity;
import java.util.List;

/**
 * CREATE BY Yo ON 2018/1/13 12:43
 */
public interface HeroDBDao {

  List<HeroEntity> getHeroesByExample();

  /**
   * 查找所有英雄
   */
  List<HeroEntity> getAllHeroes();

  /**
   * 保存
   */
  List<HeroEntity> save(List<HeroEntity> heroes);
}
