package space.aiyo.steam.services;

import space.aiyo.steam.entity.DotaItemEntity;

import java.util.List;

/**
 * Dota游戏装备接口
 * Created by yo on 2017/6/5.
 */
public interface DotaItemService {
    /**
     * TODO 参数
     */

    /**
     * 从steam平台获取游戏装备信息
     *
     * @return 游戏装备列表
     */
    List<DotaItemEntity> getItemFromSteamApi();

    /**
     * 新建或更新单条游戏装备信息
     */
    void saveItem(DotaItemEntity item);

    /**
     * 从steam平台新建或更新游戏装备信息
     */
    void saveItemFromSteamApi();

    /**
     * 获取游戏装备列表
     *
     * @return 游戏装备列表
     */
    List<DotaItemEntity> getItems();
}
