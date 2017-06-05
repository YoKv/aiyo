package space.aiyo.steam.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.aiyo.steam.contsant.SteamContsant;
import space.aiyo.steam.entity.DotaItemEntity;
import space.aiyo.steam.enums.SteamApiEnum;
import space.aiyo.steam.repository.DotaItemRepository;
import space.aiyo.steam.services.DotaItemService;
import space.aiyo.steam.util.HttpUtil;

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

    @Override
    public List<DotaItemEntity> getItemFromSteamApi() {
        String returnStr = "";
        String url = SteamContsant.STEAM_API_PATH + SteamApiEnum.GetGameItems.getUrl() + "?language=zh&key=" + SteamContsant.STEAM_KEY;
        try {
            returnStr = HttpUtil.sendGet(url);
        } catch (IOException e) {
            logger.info("调用steam接口失败: " + e.toString());
        }
        JSONObject result = (JSONObject) JSON.parseObject(returnStr).get("result");
        JSONArray itemsArray = result.getJSONArray("items");

        return JSON.parseArray(itemsArray.toJSONString(), DotaItemEntity.class);
    }

    @Override
    public void saveItem(DotaItemEntity item) {
        try {
            repository.save(item);
        } catch (Exception e) {
            logger.info("游戏装备数据存入数据库失败: " + e.toString());
        }
    }

    @Override
    public void saveItemFromSteamApi() {
        List<DotaItemEntity> items = getItemFromSteamApi();
        for (DotaItemEntity item :
                items) {
            saveItem(item);
        }
    }

    @Override
    public List<DotaItemEntity> getItems() {
        return repository.findAll();
    }
}
