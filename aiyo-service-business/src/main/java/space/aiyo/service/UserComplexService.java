package space.aiyo.service;

/**
 * CREATE BY Yo ON 2018/1/21 12:53
 * 用户信息服务
 */
public interface UserComplexService {

  void register(String username, String password);

  void userLogin();

  void phoneLogin();

}
