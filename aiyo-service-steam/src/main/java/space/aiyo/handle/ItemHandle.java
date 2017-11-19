package space.aiyo.handle;

import entity.dota.ItemEntity;
import java.util.List;

/**
 * CREATE BY Yo ON 2017/11/19 14:36
 */
public interface ItemHandle {

  /**
   * 从steam平台新建或更新游戏装备信息
   */
  List<ItemEntity> saveItemFromSteamApi();

  /**
   * 从steam平台获取装备信息
   */
  List<ItemEntity> getSteamItems();

  /**
   * 从数据库获取所有装备信息
   */
  List<ItemEntity> getItems();

  /**
   * 通过id,全名，中文名查找一个装备
   *
   * @param id id
   * @param name 全名
   * @param localizedName 中文名
   * @return ItemEntity
   */
  ItemEntity getItem(Integer id, String name, String localizedName);

  /**
   * 保存
   */
  List<ItemEntity> save(List<ItemEntity> items);

}
