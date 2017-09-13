package space.aiyo.steam.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.aiyo.steam.entity.DotaItemEntity;
import space.aiyo.steam.services.DotaItemService;

import java.util.List;

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


    /**
     * 从stram获取所有装备
     *
     * @return 装备列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/steam/items")
    @ResponseBody
    public List<DotaItemEntity> getHeroesFromSteam() {
        return dotaItemService.getSteamItems();
    }

    /**
     * 获取某个装备
     *
     * @param id            id
     * @param name          系统名
     * @param localizedName 中文名
     * @return 单个装备
     */
    @RequestMapping(method = RequestMethod.GET, value = "/item")
    @ResponseBody
    public DotaItemEntity getItem(@RequestParam(value = "id", defaultValue = "0") Integer id,
                                  @RequestParam(value = "name", defaultValue = "") String name,
                                  @RequestParam(value = "cnName", defaultValue = "") String localizedName) {
        return dotaItemService.getItem(id, name, localizedName);
    }

    /**
     * 获取所有装备
     *
     * @return 装备列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/items")
    @ResponseBody
    public List<DotaItemEntity> getItems() {
        return dotaItemService.getItems();
    }

    /**
     * 同步装备
     *
     * @return 成功数
     */
    @RequestMapping(method = RequestMethod.GET, value = "/syncItems")
    @ResponseBody
    public int syncItems() {
        return dotaItemService.saveItemFromSteamApi().size();
    }

}
