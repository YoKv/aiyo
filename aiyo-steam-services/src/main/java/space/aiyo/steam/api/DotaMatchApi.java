package space.aiyo.steam.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.aiyo.database.mongoDB.entity.match.DotaMatchEntity;
import space.aiyo.steam.services.DotaItemService;

import java.util.List;

/**
 * 游戏装备相关 RESTful API
 * Created by yo on 2017/6/5.
 */
@RestController
public class DotaMatchApi {

    @Autowired
    private DotaItemService dotaItemService;

    @RequestMapping("/matches")
    public List<DotaMatchEntity> getHeros() {

        return null;
    }
}
