package space.aiyo.service;

/**
 * CREATE BY Yo ON 2018/1/21 13:02
 */
public interface MessageService {

  /**
   * 发送短信验证码
   */
  boolean sendVerificationCode(Integer phone);

}
