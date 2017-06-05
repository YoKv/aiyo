package space.aiyo.steam.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.aiyo.steam.entity.DotaItemEntity;
import space.aiyo.steam.services.DotaItemService;

import java.util.List;

/**
 * 游戏装备相关 RESTful API
 * Created by yo on 2017/6/1.
 */
@RestController
public class DotaItemApi {

    @Autowired
    private DotaItemService dotaItemService;

    @RequestMapping("/items")
    public List<DotaItemEntity> getHeros() {
        return dotaItemService.getItems();
    }
}
