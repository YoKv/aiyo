package entity.user;

import entity.base.BaseEntity;
import java.io.Serializable;

/**
 * CREATE BY Yo ON 2018/1/21 11:32
 */
public class UserLogEntity extends BaseEntity implements Serializable {

  private Double longitude;
  private Double latitude;
  private String ip;

}
