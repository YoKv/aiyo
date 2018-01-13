package space.aiyo.dao.impl;

import entity.dota.HeroEntity;
import io.vertx.core.http.HttpClient;
import java.util.List;
import space.aiyo.dao.HeroSteamDao;

/**
 * CREATE BY Yo ON 2018/1/13 14:51
 */
public class HeroSteamDaoImpl implements HeroSteamDao{
  private HttpClient httpClient;


  @Override
  public List<HeroEntity> getHeroes() {
    return null;
  }
}
