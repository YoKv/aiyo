package space.aiyo.core.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.aiyo.core.dao.DotaItemDao;
import entity.DotaItemEntity;
import space.aiyo.val.contsant.SteamContsant;
import space.aiyo.val.enums.SteamApiEnum;
import space.aiyo.core.services.DotaItemService;
import net.HttpUtil;

import java.io.IOException;
import java.util.List;

/**
 * Dota游戏装备接口
 * Created by yo on 2017/6/5.
 */
@Service
public class DotaItemServiceImpl implements DotaItemService {


    private Logger logger = LoggerFactory.getLogger(this.getClass());
    final private DotaItemDao itemDao;

    @Autowired
    public DotaItemServiceImpl(DotaItemDao itemDao) {
        this.itemDao = itemDao;
        InnerMethod.setItemDao(itemDao);
    }

    @Override
    public List<DotaItemEntity> saveItemFromSteamApi() {
        List<DotaItemEntity> items = InnerMethod.getItemFromSteamApi();
        return itemDao.saveAll(items);
    }

    @Override
    public List<DotaItemEntity> getSteamItems() {
        return InnerMethod.getItemFromSteamApi();
    }

    @Override
    public List<DotaItemEntity> getItems() {
        return itemDao.findAll();
    }

    @Override
    public DotaItemEntity getItem(Integer id, String name, String localizedName) {
        return itemDao.findByIdOrNameOrLocalizedName(id, name, localizedName);
    }

    @Override
    public List<DotaItemEntity> save(List<DotaItemEntity> items) {
        return itemDao.saveAll(items);
    }


    /**
     * 提供静态方法的内部静态类
     */
    private static class InnerMethod {
        private static Logger logger = LoggerFactory.getLogger(DotaItemServiceImpl.InnerMethod.class);
        private static DotaItemDao itemDao;

        private static List<DotaItemEntity> getItemFromSteamApi() {
            String returnStr = "";
            StringBuilder url = new StringBuilder();
            url.append(SteamContsant.STEAM_API_PATH).append(SteamApiEnum.GET_GAME_ITEMS.getUrl());
            url.append("?language=zh&key=").append(SteamContsant.STEAM_KEY);
            try {
                returnStr = HttpUtil.sendGet(url.toString());
            } catch (IOException e) {
                logger.info("调用steam接口失败: " + e.toString());
            }
            JSONObject result = (JSONObject) JSON.parseObject(returnStr).get("result");
            JSONArray itemsArray = result.getJSONArray("items");

            return JSON.parseArray(itemsArray.toJSONString(), DotaItemEntity.class);
        }


        private static void setItemDao(DotaItemDao itemDao) {
            InnerMethod.itemDao = itemDao;
        }
    }

}
