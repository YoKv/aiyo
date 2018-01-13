package entity.dota.match;

/**
 * 玩家控制的额外的单位信息,如德鲁伊小熊
 * Created by Yo on 2017/6/10.
 */
public class AdditionalUnitsEntity {

  /**
   * The name of the unit
   */
  private String unitname;
  /**
   * ID of the top-left inventory item.
   */
  private int item0;
  /**
   * ID of the top-center inventory item.
   */
  private int item1;
  /**
   * ID of the top-right inventory item.
   */
  private int item2;
  /**
   * ID of the bottom-left inventory item.
   */
  private int item3;
  /**
   * ID of the bottom-center inventory item.
   */
  private int item4;
  /**
   * ID of the bottom-right inventory item.
   */
  private int item5;

  /**
   * 无参构造方法
   * 兼容fastjson
   */
  public AdditionalUnitsEntity() {
  }

  @Override
  public String toString() {
    return "AdditionalUnitsEntity{" + "unitname='" + unitname + '\'' + ", item0=" + item0
        + ", item1=" + item1 + ", item2=" + item2 + ", item3=" + item3 + ", item4=" + item4
        + ", item5=" + item5 + '}';
  }

  public String getUnitname() {
    return unitname;
  }

  public void setUnitname(String unitname) {
    this.unitname = unitname;
  }

  public int getItem0() {
    return item0;
  }

  public void setItem0(int item0) {
    this.item0 = item0;
  }

  public int getItem1() {
    return item1;
  }

  public void setItem1(int item1) {
    this.item1 = item1;
  }

  public int getItem2() {
    return item2;
  }

  public void setItem2(int item2) {
    this.item2 = item2;
  }

  public int getItem3() {
    return item3;
  }

  public void setItem3(int item3) {
    this.item3 = item3;
  }

  public int getItem4() {
    return item4;
  }

  public void setItem4(int item4) {
    this.item4 = item4;
  }

  public int getItem5() {
    return item5;
  }

  public void setItem5(int item5) {
    this.item5 = item5;
  }
}
