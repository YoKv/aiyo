package space.aiyo.steam.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.aiyo.database.mongoDB.entity.DotaItemEntity;
import space.aiyo.steam.services.DotaItemService;

/**
 * 游戏装备相关 RESTful API
 * Created by yo on 2017/6/5.
 */
@RestController
public class DotaItemApi {
    private DotaItemService dotaItemService;

    @Autowired
    public DotaItemApi(DotaItemService dotaItemService) {
        this.dotaItemService = dotaItemService;
    }

    @Autowired


    @RequestMapping("/items")
    public DotaItemEntity getItem() {
        return dotaItemService.findById(1);
    }
}
