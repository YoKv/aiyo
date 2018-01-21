package entity.user;

import entity.base.BaseEntity;
import enums.AccoutEnum;
import java.io.Serializable;

/**
 * CREATE BY Yo ON 2018/1/21 11:32
 */
public class AuthEntity extends BaseEntity implements Serializable {

  private AccoutEnum accoutEnum;
  private String platformId;
  private String oathId;
  private String accessToken;
  private Long expire;


  public AccoutEnum getAccoutEnum() {
    return accoutEnum;
  }

  public void setAccoutEnum(AccoutEnum accoutEnum) {
    this.accoutEnum = accoutEnum;
  }

  public String getPlatformId() {
    return platformId;
  }

  public void setPlatformId(String platformId) {
    this.platformId = platformId;
  }

  public String getOathId() {
    return oathId;
  }

  public void setOathId(String oathId) {
    this.oathId = oathId;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public Long getExpire() {
    return expire;
  }

  public void setExpire(Long expire) {
    this.expire = expire;
  }

  @Override
  public String toString() {
    return "AuthEntity{" +
        "accoutEnum=" + accoutEnum +
        ", platformId='" + platformId + '\'' +
        ", oathId='" + oathId + '\'' +
        ", accessToken='" + accessToken + '\'' +
        ", expire=" + expire +
        "} " + super.toString();
  }
}
