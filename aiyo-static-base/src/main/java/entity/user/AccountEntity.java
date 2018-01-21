package entity.user;

import entity.base.BaseEntity;
import java.io.Serializable;

/**
 * CREATE BY Yo ON 2018/1/21 11:31
 */
public class AccountEntity extends BaseEntity implements Serializable {

  private String username;

  private String password;

  private Integer phone;

  private String email;

  private Integer active;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getPhone() {
    return phone;
  }

  public void setPhone(Integer phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getActive() {
    return active;
  }

  public void setActive(Integer active) {
    this.active = active;
  }

  @Override
  public String toString() {
    return "AccountEntity{" +
        "username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", phone=" + phone +
        ", email='" + email + '\'' +
        ", active=" + active +
        "} " + super.toString();
  }
}
