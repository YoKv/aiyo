package space.aiyo.steam.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.aiyo.steam.contsant.SteamContsant;
import space.aiyo.database.mongoDB.entity.DotaItemEntity;
import space.aiyo.steam.enums.SteamApiEnum;
import space.aiyo.database.mongoDB.repository.DotaItemRepository;
import space.aiyo.steam.services.DotaItemService;
import space.aiyo.util.HttpUtil;

import java.io.IOException;
import java.util.List;

/**
 * Dota游戏装备接口
 * Created by yo on 2017/6/5.
 */
@Service
public class DotaItemServiceImpl implements DotaItemService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DotaItemRepository repository;

    @Autowired
    public DotaItemServiceImpl(DotaItemRepository repository) {
        this.repository = repository;
    }

    /**
     * 通过id查找一个装备
     *
     * @param id
     * @return DotaItemEntity
     */
    @Override
    public DotaItemEntity findById(int id) {
        return repository.findDotaItemEntityById(id);
    }


    @Override
    public void saveItemFromSteamApi() {
        List<DotaItemEntity> items = getItemFromSteamApi();
        saveAll(items);
    }

    private List<DotaItemEntity> getItemFromSteamApi() {
        String returnStr = "";
        String url = SteamContsant.STEAM_API_PATH + SteamApiEnum.GET_GAME_ITEMS.getUrl() + "?language=zh&key=" + SteamContsant.STEAM_KEY;
        try {
            returnStr = HttpUtil.sendGet(url);
        } catch (IOException e) {
            logger.info("调用steam接口失败: " + e.toString());
        }
        JSONObject result = (JSONObject) JSON.parseObject(returnStr).get("result");
        JSONArray itemsArray = result.getJSONArray("items");

        return JSON.parseArray(itemsArray.toJSONString(), DotaItemEntity.class);
    }

    /**---------------------------------------------------------------数-------------------------------------------------------------**/
    /**---------------------------------------------------------------据-------------------------------------------------------------**/
    /**---------------------------------------------------------------库-------------------------------------------------------------**/
    /**---------------------------------------------------------------方-------------------------------------------------------------**/
    /**---------------------------------------------------------------法-------------------------------------------------------------**/
    /**---------------------------------------------------------------封-------------------------------------------------------------**/
    /**---------------------------------------------------------------装-------------------------------------------------------------**/

    private List<DotaItemEntity> saveAll(List<DotaItemEntity> items) {
        return repository.saveAll(items);
    }
}
