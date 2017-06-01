package space.aiyo.steam.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.aiyo.steam.entity.Dota2HeroEntity;
import space.aiyo.steam.services.HeroService;

import java.util.List;

/**
 * 英雄相关 RESTful API
 * Created by yo on 2017/6/1.
 */
@RestController
public class HeroApi {

    @Autowired
    private HeroService heroService;

    @RequestMapping("/heroes")
    public List<Dota2HeroEntity> getHeros() {
        return heroService.getHeroes();
    }
}
