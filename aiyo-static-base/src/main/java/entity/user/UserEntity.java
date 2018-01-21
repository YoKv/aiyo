package entity.user;

import entity.base.BaseEntity;
import java.io.Serializable;

/**
 * CREATE BY Yo ON 2018/1/21 11:31
 */
public class UserEntity extends BaseEntity implements Serializable {


  private Long accountId;
  private Long mainSteamId;
  private String nickName;
  private String name;
  private String address;
  private String sex;

  public Long getAccountId() {
    return accountId;
  }

  public void setAccountId(Long accountId) {
    this.accountId = accountId;
  }

  public Long getMainSteamId() {
    return mainSteamId;
  }

  public void setMainSteamId(Long mainSteamId) {
    this.mainSteamId = mainSteamId;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  @Override
  public String toString() {
    return "UserEntity{" +
        "accountId=" + accountId +
        ", mainSteamId=" + mainSteamId +
        ", nickName='" + nickName + '\'' +
        ", name='" + name + '\'' +
        ", address='" + address + '\'' +
        ", sex='" + sex + '\'' +
        "} " + super.toString();
  }
}
