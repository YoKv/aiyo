package space.aiyo.core.services;

import entity.DotaItemEntity;

import java.util.List;

/**
 * Dota游戏装备接口
 * Created by yo on 2017/6/5.
 */
public interface DotaItemService {
    /**
     * 从steam平台新建或更新游戏装备信息
     */
    List<DotaItemEntity> saveItemFromSteamApi();

    /**
     * 从steam平台获取装备信息
     */
    List<DotaItemEntity> getSteamItems();

    /**
     * 从数据库获取所有装备信息
     */
    List<DotaItemEntity> getItems();

    /**
     * 通过id,全名，中文名查找一个装备
     *
     * @param id            id
     * @param name          全名
     * @param localizedName 中文名
     * @return DotaItemEntity
     */
    DotaItemEntity getItem(Integer id, String name, String localizedName);

    /**
     * 保存
     */
    List<DotaItemEntity> save(List<DotaItemEntity> items);

}
