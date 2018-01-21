package space.aiyo.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import space.aiyo.service.UserComplexService;

/**
 * CREATE BY Yo ON 2018/1/21 13:04
 */
@Service("userComplexService")
public class UserComplexServiceImpl implements UserComplexService {

  @Value("${user.salt}")
  private String salt;

  @Override
  public void register(String username, String password) {

  }

  @Override
  public void userLogin() {

  }

  @Override
  public void phoneLogin() {

  }
}
