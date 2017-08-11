package space.aiyo.steam.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.aiyo.database.mongoDB.entity.DotaHeroEntity;
import space.aiyo.steam.services.DotaHeroService;

import java.util.List;

/**
 * 英雄相关 RESTful API
 * Created by yo on 2017/6/1.
 */
@RestController
public class DotaHeroApi {

    @Autowired
    private DotaHeroService dotaHeroService;

    @RequestMapping("/heroes")
    public List<DotaHeroEntity> getHeros() {
        return dotaHeroService.getHeroes();
    }

    @RequestMapping("/syncHeroes")
    public List<DotaHeroEntity> syncHeroes() {
        return dotaHeroService.saveHeroFromSteamApi();
    }
}
