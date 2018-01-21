package entity.base;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * CREATE BY Yo ON 2018/1/21 11:37
 */
public class BaseEntity implements Serializable {

  private Long id;
  private Timestamp addtime;
  private Timestamp updateTime;
  private Integer delete;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Timestamp getAddtime() {
    return addtime;
  }

  public void setAddtime(Timestamp addtime) {
    this.addtime = addtime;
  }

  public Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Timestamp updateTime) {
    this.updateTime = updateTime;
  }

  public Integer getDelete() {
    return delete;
  }

  public void setDelete(Integer delete) {
    this.delete = delete;
  }

  @Override
  public String toString() {
    return "BaseEntity{" +
        "id=" + id +
        ", addtime=" + addtime +
        ", updateTime=" + updateTime +
        ", delete=" + delete +
        '}';
  }
}
