package entity;

/**
 * CREATE BY Yo ON 2018/1/14 01:04
 */
public class BaseEntity extends BottomEntity {


  private int insertTime;
  private int updateTime;

  public int getInsertTime() {
    return insertTime;
  }

  public void setInsertTime(int insertTime) {
    this.insertTime = insertTime;
  }

  public int getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(int updateTime) {
    this.updateTime = updateTime;
  }
}
