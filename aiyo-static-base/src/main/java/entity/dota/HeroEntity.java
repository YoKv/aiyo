package entity.dota;

/**
 * Dota2英雄
 * Created by Yo on 2017/5/26.
 */
public class HeroEntity {

  private int id;//id，以字母排序
  private String name;//全名
  private String localized_name;//中文名zh

  /**
   * 无参构造方法
   * 兼容fastjson
   */
  public HeroEntity() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocalized_name() {
    return localized_name;
  }

  public void setLocalized_name(String localized_name) {
    this.localized_name = localized_name;
  }

  @Override
  public String toString() {
    return "HeroEntity{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", localized_name='" + localized_name + '\'' +
        '}';
  }
}
