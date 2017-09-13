package space.aiyo.steam.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.aiyo.steam.entity.DotaHeroEntity;
import space.aiyo.steam.services.DotaHeroService;

import java.util.List;

/**
 * 英雄相关 RESTful API
 * Created by yo on 2017/6/1.
 */
@RestController
@RequestMapping(produces = "application/json")
public class DotaHeroApi {
    final private DotaHeroService dotaHeroService;

    @Autowired
    public DotaHeroApi(DotaHeroService dotaHeroService) {
        this.dotaHeroService = dotaHeroService;
    }

    /**
     * 从stram获取所有英雄
     *
     * @return 英雄列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/steam/heroes")
    @ResponseBody
    public List<DotaHeroEntity> getHeroesFromSteam() {
        return dotaHeroService.getSteamHeroes();
    }

    /**
     * 获取某个英雄
     *
     * @param id            id
     * @param name          系统名
     * @param localizedName 中文名
     * @return 单个英雄
     */
    @RequestMapping(method = RequestMethod.GET, value = "/hero")
    @ResponseBody
    public DotaHeroEntity getHero(@RequestParam(value = "id", defaultValue = "0") Integer id,
                                  @RequestParam(value = "name", defaultValue = "") String name,
                                  @RequestParam(value = "cnName", defaultValue = "") String localizedName) {
        return dotaHeroService.getHero(id, name, localizedName);
    }

    /**
     * 获取所有英雄
     *
     * @return 英雄列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/heroes")
    @ResponseBody
    public List<DotaHeroEntity> getHeroes() {
        return dotaHeroService.getHeroes();
    }

    /**
     * 同步英雄
     *
     * @return 成功数
     */
    @RequestMapping(method = RequestMethod.GET, value = "/syncHeroes")
    @ResponseBody
    public int syncHeroes() {
        return dotaHeroService.saveHeroFromSteamApi().size();
    }


}
