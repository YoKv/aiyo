package entity.dota;

import entity.BottomEntity;

/**
 * Dota2英雄
 * Created by Yo on 2017/5/26.
 */
public class HeroEntity extends BottomEntity {

  private String name;//全名
  private String localizedName;//中文名zh

  /**
   * 无参构造方法
   * 兼容fastjson
   */
  public HeroEntity() {
  }

  public HeroEntity(long id, String name, String localizedName) {
    super(id);
    this.name = name;
    this.localizedName = localizedName;
  }

  @Override
  public String toString() {
    return String.format("HeroEntity[id=%base, name='%base', localizedName='%base']", getId(), name,
        localizedName);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocalizedName() {
    return localizedName;
  }

  public void setLocalizedName(String localizedName) {
    this.localizedName = localizedName;
  }
}
