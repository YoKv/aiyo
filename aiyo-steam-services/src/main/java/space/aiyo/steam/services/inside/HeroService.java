package space.aiyo.steam.services.inside;

import space.aiyo.steam.entity.Dota2HeroEntity;

import java.util.List;

/**
 * Created by yo on 2017/5/27.
 */
public interface HeroService {
    List<Dota2HeroEntity> getHeroFromSteamApi();
}
