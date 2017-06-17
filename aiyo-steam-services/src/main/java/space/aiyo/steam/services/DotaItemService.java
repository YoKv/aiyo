package space.aiyo.steam.services;

import space.aiyo.steam.entity.DotaItemEntity;

import java.util.List;

/**
 * Dota游戏装备接口
 * Created by yo on 2017/6/5.
 */
public interface DotaItemService {

    /**
     * 通过id查找一个装备
     *
     * @param id
     * @return DotaItemEntity
     */
    DotaItemEntity findById(int id);

    /**
     * 从steam平台新建或更新游戏装备信息
     */
    void saveItemFromSteamApi();

}
