package space.aiyo.service.impl;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import space.aiyo.enums.RedisKey;
import space.aiyo.service.MessageService;

/**
 * CREATE BY Yo ON 2018/1/21 13:03
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {

  @Autowired
  private RedisTemplate<String, String> redisTemplate;
  @Value("${user.verificationCodeExpire}")
  private long verificationCodeExpire;

  @Override
  public boolean sendVerificationCode(Integer phone) {
    //切面控制频率，验证码未过期不重新生成 TODO

    String verificationCode =
        "" + new Random().nextInt(10) + new Random().nextInt(10) + new Random().nextInt(10)
            + new Random().nextInt(10);
    //TODO 多线程 futureTask实现
    redisTemplate.opsForValue()
        .set(RedisKey.VERIFICATION_CODE.getKey() + phone, verificationCode, verificationCodeExpire,
            TimeUnit.SECONDS);

    send(phone, verificationCode);

    return false;
  }


  private void send(Integer phone, String message) {

  }

}
