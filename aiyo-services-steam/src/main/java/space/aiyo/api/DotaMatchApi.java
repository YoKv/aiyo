package space.aiyo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.aiyo.core.entity.DotaMatchEntity;
import space.aiyo.core.services.DotaMatchService;

/**
 * 游戏比赛相关 RESTful API
 * Created by yo on 2017/6/5.
 */
@RestController
public class DotaMatchApi {
    private DotaMatchService dotaMatchService;

    @Autowired
    public DotaMatchApi(DotaMatchService dotaMatchService) {
        this.dotaMatchService = dotaMatchService;
    }

    @RequestMapping("/counts")
    @ResponseBody
    public int counts() {
        return dotaMatchService.count();
    }

    /**
     * 获取某个比赛
     * @return 单个比赛
     */
    @RequestMapping(method = RequestMethod.GET, value = "/match")
    @ResponseBody
    public DotaMatchEntity getMatch(@RequestParam(value = "match", defaultValue = "0") DotaMatchEntity match) {
        return null;
    }
    @RequestMapping("/matchById")
    public DotaMatchEntity getbyid(@RequestParam(value = "id", defaultValue = "0") long id ) {
        return null;
    }

}
