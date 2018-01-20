package space.aiyo.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * CREATE BY Yo ON 2018/1/20 13:50
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

  @Bean
  public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {

    return null;
  }

}
