package space.aiyo.dao;

import entity.dota.HeroEntity;
import java.util.List;

/**
 * CREATE BY Yo ON 2018/1/13 14:41
 */
public interface HeroSteamDao {
  List<HeroEntity> getHeroes();

}
